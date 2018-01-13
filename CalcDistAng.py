class CalcDistAng():
	
	def calcDist(self, distConst, length): #the length of the object
		#the distance and size is inversely proportional
		#uses formula dist * rectSize = constant
		if(length > 0 and distConst > 0):
			return distConst / length;
		return -1;


	#calculates the angle to turn to face the item in degrees
	def calcAngleDeg(self, angleConst, screenwidth, x):
		return self.calcAngleRad(angleConst, screenwidth, x) * 180.0 / math.pi

	#calculates the angle in radians
	#we need to turn to be centered with the back of the board
	def calcAngleRad(self, angleConst, screenwidth, x):
		xRespectToCenter = self.calcXRespectToCenter(screenwidth, x)
		#returns it in radians
		return math.atan(xRespectToCenter / angleConst)

	#helper method to calculate the horizontal distance
	#between the center of the screen and the point in pixels
	def calcXRespectToCenter(self, screenwidth, x):
		#if the point is on the right side of the screen, POSITIVE
		#point on left side of screen, NEGATIVE
		return (pinX - screenwidth / 2);
		#return math.fabs(pinX - SCREEN_WIDTH / 2);