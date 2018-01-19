# Echo server program
import socket
import os
import time
HOST = ''                 # Symbolic name meaning all available interfaces
PORT = 50007              # Arbitrary non-privileged port
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((HOST, PORT))
print "listening..."
s.listen(1)
global conn
conn, addr = s.accept()
print 'Connected by', addr
global data
coord1 = 14.8
coord2 = 11.14
coord3 = 11.8
coord4 = 20.56
def ping():
    #data = os.system("ping -t 2000 -c 1 10.19.89.50")
    conn.send('s\n')
    data = conn.recv(1024)
    print "data: "+str(data)
    if not data: return False
    elif 'c\n' in data: return True
    else: return False

while 1:
    time.sleep(0.1)
    print "yoe"
    conn.send('s\n')
    data = conn.recv(1024)
    print "data: "+str(data)
    if not data: recieve = False
    elif 'c\n' in data: recieve = True
    else: recieve = False
    print str(recieve)
    if recieve:
        print "ff"
        s.settimeout(2.0)
        try:
            data = conn.recv(1024)
        except timeout:
            print "wererewrwe"
        if "function triggered" in data: conn.send("coordinates: "+str(coord1)+" "+str(coord2)+" "+str(coord3)+" "+str(coord4)+"\n")
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
    coord1 = coord1 + 1
    coord2 = coord2 + 1
    coord3 = coord3 + 1
    coord4 = coord4 + 1

conn.close()
