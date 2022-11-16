import im
import time

server = im.IMServerProxy('http://webdev.cs.manchester.ac.uk/~u15794sg/IMServer.php')

def communicate(thisUser, otherUser):
	server['message'] = ''
	waiting_for_reply = True
	num_of_users = 2
	while True:
		while (num_of_users == 2):
			if ((server['switch_to'].replace('\r\n','')) == thisUser):
				if(server['message'] != '\r\n'):
					print '*** You recieved a message: ' + server['message']
					if(server['message'] == 'quit\r\n'):
						num_of_users = 1;
				myMessage = raw_input('Type your message: ')
				server['message'] = myMessage
				waiting_for_reply = True
				server['switch_to'] = otherUser
			else:
				if(waiting_for_reply):
					print ''
					print 'Waiting...    Another user is typing...'
					waiting_for_reply = False
				time.sleep(2)

if server['user1Status'] == 'Unoccupied\r\n':
	server['user1Status'] = 'Occupied'
	server['switch_to'] = 'user1'
	communicate('user1', 'user2')
else:
	server['user2Status'] = 'Occupied'
	communicate('user2', 'user1')
