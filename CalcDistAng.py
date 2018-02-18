import math
class CalcDistAng():
	
	def __init__(self):
		self.SCREEN_WIDTH = 1280
		self.SCREEN_HEIGHT = 720
		self.FIELD_OF_VIEW_RAD = 70.42 * math.pi / 180.0

		#arbitrary distance in pixels from the camera to the background
		self.ANGLE_CONST = (self.SCREEN_WIDTH / 2.0) / math.tan(self.FIELD_OF_VIEW_RAD / 2.0)


		self.DIST_CONSTANT = 15534.5065179
		self.DIST_CONSTANT = self.DIST_CONSTANT * 720 / 1080.0

	def calcDistAndAdjAngUsingAdjs(self, x1, y1, x2, y2, offsetLength):
		adjLength = self.calcAdjLength(x1, y1, x2, y2)

		print "adjLength: " + str(adjLength)
		
		adjX1, adjX2 = self.calcAdjXcoords(x1, y1, x2, y2)
		print "adjXCoords: " + str(self.calcAdjXcoords(x1, y1, x2, y2))

		dist, adjAng = self.calcDistAndAdjAng(adjX1, y1, adjX2, y1+adjLength, offsetLength)
		
		return dist, adjAng


	def calcDistAndAdjAng(self, x1, y1, x2, y2, offsetLength):
		dist, ang = self.calcDistAndAng(x1, y1, x2, y2)
		distPerpendicular = self.calcDistPerpendicular(math.fabs(y2-y1))
		angAdj = self.calcAngleAdjustedDeg(ang, distPerpendicular, offsetLength)
		return dist, angAdj


	def calcDistAndAng(self, x1, y1, x2, y2):
		ang = self.calcAngleDeg((x1 + x2)/2.0)
		length = math.fabs(y2 - y1)
		dist = self.calcDistHypotenuseDeg(length, ang)

		return dist, ang

	def calcAdjLength(self, x1, y1, x2, y2):
		origLength = math.fabs(y2 - y1)
		width = math.fabs(x2 - x1)
		realRatio = (origLength / width)

		diagCubeRatio = 11 / (13 * math.sqrt(2)) #+ 0.1  #just as a buffer
		straightCubeRatio = 11/13.0

		print diagCubeRatio
		print realRatio

		print "diag thres: " + str(realRatio/ diagCubeRatio)
		print "straight thres: " + str(realRatio/straightCubeRatio)



		numStacked = math.ceil(realRatio/straightCubeRatio)
		#while (origLength / width >= ((numStacked + 1) * diagCubeRatio + 0.1) ):
			#print numStacked
			#print str((numStacked + 1) * diagCubeRatio)
			#numStacked+=1
		
		print numStacked
		return float(origLength) / numStacked

	def calcAdjXcoords(self, x1, y1, x2, y2):
		length = math.fabs(y2 - y1)
		origWidth = math.fabs(x2 - x1)
		realRatio = (origWidth / length)

		#diagCubeRatio = 11 / (13 * math.sqrt(2)) #+ 0.1  #just as a buffer
		straightCubeRatio = 13/11.0
		diagCubeRatio = 13*math.sqrt(2) / 11

		#print diagCubeRatio
		print realRatio

		#print "diag thres: " + str(realRatio/ diagCubeRatio)
		print "straight thres: " + str(realRatio/straightCubeRatio)
		print "dig thres: " + str(realRatio/diagCubeRatio)


		numNextTo = math.ceil(realRatio/diagCubeRatio)
		#while (origLength / width >= ((numNextTo + 1) * diagCubeRatio + 0.1) ):
			#print numNextTo
			#print str((numNextTo + 1) * diagCubeRatio)
			#numNextTo+=1
		
		print "numNextTo: "+str(numNextTo)
		

		adjWidth = float(origWidth) / numNextTo
		print "adjWidth: "+str(adjWidth)

		retx1 = self.calcCenterX1(x1, numNextTo, adjWidth)
		retx2 = retx1+adjWidth

		return retx1, retx2


	def calcCenterX1(self, x1, numNextTo, adjWidth, screenwidth=None):
		if screenwidth is None:
			screenwidth = self.SCREEN_WIDTH

		centerX = screenwidth/2.0
		print centerX

		numBoxesToRight = math.floor((centerX - x1) / adjWidth)
		print numBoxesToRight

		if numBoxesToRight <=0:
			return x1
		elif numBoxesToRight < numNextTo:
			return x1 + numBoxesToRight*adjWidth
		else:
			return x1 + (numNextTo-1)*adjWidth

	def calcDistPerpendicular(self, length, distConst=None): #the length of the object
		if distConst is None:
			distConst = self.DIST_CONSTANT

		#the distance and size is inversely proportional
		#uses formula dist * rectSize = constant
		if(length > 0 and distConst > 0):
			return distConst / length;
		return -1;

	def calcDistHypotenuseDeg(self, length, angle, distConst=None):
		if distConst is None:
			distConst = self.DIST_CONSTANT

		return self.calcDistPerpendicular(length, distConst) / math.cos(angle*math.pi/180)

	def calcAngleAdjustedDeg(self, angleDeg, distPerpendicular, offsetLength):
		tanOfAngAdj = distPerpendicular*math.tan(angleDeg * math.pi / 180)/(distPerpendicular+offsetLength)
		return math.atan(tanOfAngAdj) * 180 / math.pi
	

	#calculates the angle to turn to face the item in degrees
	def calcAngleDeg(self, x, angConst=None, screenwidth=None):
		if angConst is None:
			angConst = self.ANGLE_CONST

		if screenwidth is None:
			screenwidth = self.SCREEN_WIDTH

		return self.calcAngleRad(x, angConst, screenwidth) * 180.0 / math.pi

	#calculates the angle in radians
	#we need to turn to be centered with the back of the board
	def calcAngleRad(self, x, angConst=None, screenwidth=None):
		if angConst is None:
			angConst = self.ANGLE_CONST

		if screenwidth is None:
			screenwidth = self.SCREEN_WIDTH

		xRespectToCenter = self.calcXRespectToCenter(x, screenwidth)
		#returns it in radians
		return math.atan(xRespectToCenter / angConst)

	#helper method to calculate the horizontal distance
	#between the center of the screen and the point in pixels
	def calcXRespectToCenter(self, x, screenwidth=None):
		if screenwidth is None:
			screenwidth = self.SCREEN_WIDTH

		#if the point is on the right side of the screen, POSITIVE
		#point on left side of screen, NEGATIVE
		return (x - screenwidth / 2.0);
		#return math.fabs(pinX - SCREEN_WIDTH / 2);

	#calculates the midpoint given the x value or the width
	def calculateMidpoint(self, x, w):
		retx = x + w/2.0
		return retx

	#determines whether the x or y coordinate is on the edge of the screen
	def onEdge(self, screenWidth, x1, x2):
		if(x1 == 0 or x1 == screenWidth):
			return True
		if(x2 == 0 or x2 == screenWidth):
			return True
		return False

# print "here"
foo = CalcDistAng()
while True:
	width = input("enter width: ")
	height = input("enter height: ")
 	print foo.calcAdjXcoords(0, 0, width, height)

