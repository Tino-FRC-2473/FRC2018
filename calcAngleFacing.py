class CalcAngleFacing():
	

	"""Angle constant (b), 
	closer edge/left (a), second edge (c) with respect to the center of the screen
	theoretical length"""
	def calcAngleFacingLeftCloserDeg(self, angConst, leftEdge, rightEdge, theoLength):
		self.calcAngleFacingLeftCloserRad(angConst, leftEdge, rightEdge, theoLength);

	"""Angle constant (b), 
	closer edge/left (a), second edge (c) with respect to the center of the screen
	theoretical length"""
	def calcAngleFacingLeftCloserRad(self, angConst, leftEdge, rightEdge, theoLength):
		k = math.sqrt(angConst*angConst + rightEdge*rightEdge);

		numerator2 = angConst*(rightEdge - leftEdge);
		denominator2 = theoLength * k

		return math.asin(angConst / k) - math.asin(numerator2 / denominator2);


	"""Angle constant (b), 
	second edge (c), closer edge/right (a) with respect to the center of the screen
	theoretical length"""
	def calcAngleFacingRightCloserDeg(self, angConst, leftEdge, rightEdge, theoLength):
		self.calcAngleFacingRightCloserRad(angConst, leftEdge, rightEdge, theoLength);

	"""Angle constant (b), 
	second edge (c), closer edge/right (a) with respect to the center of the screen
	theoretical length"""
	def calcAngleFacingRightCloserRad(self, angConst, leftEdge, rightEdge, theoLength):
		k = math.sqrt(angConst*angConst + leftEdge*leftEdge);

		numerator1 = angConst*(rightEdge - leftEdge);
		denominator1 = theoLength * k

		return math.asin(numerator1 / denominator1) - math.asin(angConst / k)