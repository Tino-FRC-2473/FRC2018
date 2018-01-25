# Echo server program
from socket import error as SocketError
import rospy
from std_msgs.msg import String
import sys, select, termios, tty, time
import socket
import os
import block_calcDistAng
import time
HOST = ''                 # Symbolic name meaning all available interfaces
PORT = 5805              # Arbitrary non-privileged port
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((HOST, PORT))
print "listening..."
s.listen(1)
global conn
conn, addr = s.accept()
print 'Connected by', addr
global data
global coordstring
global coordstring1
coordstring1 = "dist -777 ang -777"
coordstring = "dist -777 ang -777"
calcs = block_calcDistAng.BlockCalcDistAng()
rospy.init_node('server')

def callback(data):
    global coordstring
    global coordstring1
    process = str(data)[7:(len(str(data))-1)]
    process = process.split()
    dist, ang = calcs.calcAngAndDist(float(process[0]),float(process[1]),float(process[2]),float(process[3]))
    
    coordstring ="dist " +  str(dist)+" ang "+str(ang)

    #print coordstring

def get_coords():
    return str(coordstring)

def listener():
    rospy.Subscriber('/coordinates', String, callback)

def ping():
    global conn
    #data = os.system("ping -t 2000 -c 1 10.19.89.50")
    conn.send('s\n')
    try:
        data = conn.recv(1024)
        print "data: "+str(data)
        if not data: return False
        elif 'c\n' in data: return True
        else: return False
    except SocketError as e:
        print "fuck you"
	return False

listener()
while 1:
    time.sleep(0.1)
    recieve = ping()
    print str(recieve)
    if recieve:
        s.settimeout(2.0)
        try:
            data = conn.recv(1024)
        except timeout:
            print "wererewrwe"
        print coordstring
        print coordstring1
        if "r" in data:
            if coordstring != coordstring1:
            	conn.send(coordstring)
            	coordstring1 = str(coordstring)
            else:
            	coordstring = "dist -777 ang -777"
            	coordstring1 = str(coordstring)
            	conn.send(coordstring)
        if "done" in data: quit()
    else:
        print "gege"
        conn.close()
        print "listening..."
        s.settimeout(None)
        s.listen(1)
        conn, addr = s.accept()
        print "connected!"
    print "---------"

conn.close()
