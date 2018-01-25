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

	def calcAngAndDist(self, x1, y1, x2, y2):
		ang = self.calcAngleDeg((x1 + x2)/2.0)
		length = math.fabs(y2 - y1)
		dist = self.calcDistHypotenuseDeg(length, ang)

		return dist, ang

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

print "here"
foo = CalcDistAng()
print "here2"
print foo.calcDistPerpendicular(22)
