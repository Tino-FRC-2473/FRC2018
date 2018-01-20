##ADD IMPORTS
import math
import cv2
import numpy as np
#import os

#import CalcDistAng as calc

class BlockCalcDistAng:

	def __init__(self):
		#for distance
		self.distance = -1
		
		self.DIST_CONSTANT = 15534.5065179
		self.DIST_CONSTANT = self.DIST_CONSTANT * 720 / 1080.0
		#self.DIST_CONSTANT = 14964.5604214
		#self.OLDDIST_CONSTANT = 3234.375 * 11 / 5

		#for angle
		#calc the angle constant
		self.FIELD_OF_VIEW_RAD = 70.42 * math.pi / 180.0 #note: total not just half of the screen
		
		#make this less sketch
		self.SCREEN_WIDTH = 1280
		self.SCREEN_HEIGHT = 720
		self.ANGLE_CONST = (self.SCREEN_WIDTH / 2.0) / math.tan(self.FIELD_OF_VIEW_RAD / 2.0)
		
		#calc angle
		self.angle = 404
		#returns the left top and right bottom coordinates

	def calcAngAndDist(self, x1, y1, x2, y2):
		ang = self.calcAngleDeg((x1 + x2)/2.0)
		length = math.fabs(y2 - y1)
		dist = self.calcDistHypotenuseDeg(length, ang)

		return dist, ang

	#calculates the distance to the back of the board with the peg on it
	#+/- about an inch depending on the case
	def calcDistPerpendicular(self, length): #the length of the rectangle
		#the distance and size is inversely proportional
		#uses formula dist * rectSize = constant
		if(length > 0):
			return self.DIST_CONSTANT / length;
		return -1;

	#calculates the supposedly actual distance
	#angle is in degrees
	def calcDistHypotenuseDeg(self, length, angle):
		return self.calcDistPerpendicular(length) / math.cos(angle*math.pi/180)

	#calculates the angle in degrees
	#we need to turn to be centered with the back of the board
	def calcAngleDeg(self, x):
		return self.calcAngleRad(x) * 180.0 / math.pi

	#calculates the angle in radians
	#we need to turn to be centered with the back of the board
	def calcAngleRad(self, pinX):
		pinDistToCenter = self.calcPinDist(pinX)
		#returns it in radians
		return math.atan(pinDistToCenter / self.ANGLE_CONST)

	#helper method to calculate the horizontal distance
	#between the center of the screen and the peg in pixels
	def calcPinDist(self, pinX):
		#if the peg is on the right side of the screen, POSITIVE
		#peg on left side of screen, NEGATIVE
		return (pinX - self.SCREEN_WIDTH / 2);
		#return math.fabs(pinX - SCREEN_WIDTH / 2);

	#calculates the approximate position of the peg on the screen
	def pinPosition(self, x, w, y, h):
		retx = x + w/2.0
		rety = y + h/2.0
		return (int(retx), int(rety))

	#calculates the approximate position of the peg on the screen
	def pinPositionX(self, x, w, y, h):
		retx = x + w/2.0
		return retx;



	def runCV(self, camera):
		#These are the values that the thing will return...
		distance = -1
		angle = 404
		onedge = False


		#print "run in method"
		_, frame = camera.read()
		
		print str(frame.shape[:2])

		#convert to hsv
		hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

		# zero_red = np.array([0, 100, 100]);
		# low_red = np.array([10, 255, 255]);
		# high_red = np.array([160, 100, 100]);
		# pi_red = np.array([180, 255, 255]);
		
		#make mask
		# maskLow = cv2.inRange(hsv, zero_red, low_red)
		# maskHigh = cv2.inRange(hsv, high_red, pi_red)
		# mask = maskLow + maskHigh

		#50 * 2.55, 65*2.55
		#81.5, 96*2.55, 

		#53, 27 (68.85), 75 (181.25)
		yellow = np.array([27.5, 127.5, 181.05])
		low_yellow = np.array([24, 80, 130]) #used to be 100 for the middle
		high_yellow = np.array([30, 255, 255])
		mask = cv2.inRange(hsv, low_yellow, high_yellow)

		cv2.imshow("mask", mask)
		
		#find controus based on mask
		_, contour,_ = cv2.findContours(mask, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)


		max_area = 0
		mx,my,mw,mh = 0, 0, 0, 0 #variables x,y,width,height associated with the biggest rectangle

		secmax_area = 0
		sx,sy,sw,sh = 0, 0, 0, 0 #variables x,y,width,height associated with the second biggest rectangle

		thirdmax_area = 0
		tx,ty,tw,th = 0, 0, 0, 0 #variables x,y,width,height associated with the third biggest rectangle

		#initializes rectangle variables for side cases
		#these will be used to calculate the distance and angle
		modmx, modmy, modmw, modmh = mx, my, mw, mh
		# not sure if this is necessary modsx, modsy, modsw, modsh = sx, sy, sw, sh 

		#finds the two biggest rectangles
		for cnt in contour:
			#gets the x, y of the top left corner and 
			#width and height of the rectangle
			x,y,w,h = cv2.boundingRect(cnt)
			#cv2.rectangle(frame,(x,y),(x+w,y+h),(0,150,255),thickness=3)

			#gets area of the rectangle
			area = cv2.contourArea(cnt)
			#print str(area)

			#checks if the rectangle is greater than the max_area so far and
			#sets the variables to accommodate the change
			if(area >= max_area):
				thirdmax_area = secmax_area
				secmax_area = max_area
				max_area = area
				
				tx = sx
				ty = sy
				tw = sw
				th = sh

				sx = mx
				sy = my
				sw = mw
				sh = mh

				mx = x
				my = y
				mw = w
				mh = h
			#checks if the rectangle is greater than the secmax_area so far and
			#sets the variables to accommodate the change
			elif(area >= secmax_area):
				thirdmax_area = secmax_area
				secmax_area = area
				
				tx = sx
				ty = sy
				tw = sw
				th = sh

				sx = x
				sy = y
				sw = w
				sh = h
			#checks if the rectangle is greater than the thirdmax_area so far and
			#sets the variables to accommodate the change
			elif(area >= thirdmax_area):
				thirdmax_area = area

				tx = x
				ty = y
				tw = w
				th = h
		if(max_area > 0):
			#draws a green rect on the biggest rectangle found
			cv2.rectangle(frame,(mx,my),(mx+mw,my+mh),(0,255,0),thickness=1)
			
			modmx, modmy, modmw, modmh = mx, my, mw, mh

			if(secmax_area > 0):
				cv2.rectangle(frame,(sx,sy),(sx+sw,sy+sh),(0,0,255),thickness=1)

				errorThres = 15 #change this on the jetson probably
				

				
				#check if the second rectangle is in the max rect area
				if(sx >= mx-errorThres and #leftmost s coordinate is on the right of mx
					sx+sw <= mx+mw+errorThres and # and #rightmost s coordinate is within right m coordinate
					#ty >= sy-errorThres and ty+th <= sy+sh+errorThres and #there shouldn't be a limit to sy
					#(ty <= my+errorThres or ty+th >= my+mh-errorThres)):
					
					(sy < my or sy+sh > my+mh)): #we need the y coordinate to be outside the box
					modmx, modmy, modmw, modmh = mx, sy, mw, mh + my-sy
					cv2.rectangle(frame,(mx,sy),(mx+mw,my+mh),(255,0,255),thickness=1)
					cv2.rectangle(frame,(modmx,modmy),(modmx+modmw,modmy+modmh),(255,255,0),thickness=1)
			

			angle = self.calcAngleDeg(self.pinPositionX(modmx, modmw, modmy, modmh));
			distance = self.calcDistPerpendicular(modmh)
			distanceHyp = self.calcDistHypotenuseDeg(modmh, angle)

			cv2.circle(frame, self.pinPosition(modmx, modmw, modmy, modmh), 1, (255, 0, 0), thickness=5)

			#displays data on the screen such as the angle and distance
			cv2.putText(frame, "ANG: " + str(angle), (0, 50), cv2.FONT_HERSHEY_SIMPLEX, 2, 255)
			#cv2.putText(frame, "BANG: " + str(calcAngleDeg(diagPinX)), (0, 250), cv2.FONT_HERSHEY_SIMPLEX, 2, 255)
			cv2.putText(frame, "DIST: " + str(distance), (0, 100), cv2.FONT_HERSHEY_SIMPLEX, 2, 255)

			#displays the lengths of the original rectangles
			cv2.putText(frame, "mh: " + str(modmh) + ", mw: " + str(modmw), (0, 150), cv2.FONT_HERSHEY_SIMPLEX, 2, 255)
			
			cv2.putText(frame, "DIST HYP: " + str(distanceHyp), (0, 200), cv2.FONT_HERSHEY_SIMPLEX, 2, 255)


			#displays data on the console such as the angle and distance
			print "mh: " + str(modmh) + ", mw: " + str(modmw)
			#print "modmh: " + str(modmh) + ", modsh: " + str(modsh)
			print "Distance: " + str(distance)
			print "Angle: " + str(angle)
			print "--------------------"

		


		cv2.waitKey(3)
		cv2.imshow("Frame", frame)
		return distance, angle, onedge
		#return frame


#foo = BlockCalcDistAng()
#camera = cv2.VideoCapture(0)

#fourcc = cv2.VideoWriter_fourcc(*'XVID')
#out = cv2.VideoWriter('output.avi',fourcc, 29.97, (foo.SCREEN_WIDTH, foo.SCREEN_HEIGHT))
#while True:
	#foo.runCV(camera)
	#out.write(frame)

#out.close()
	

