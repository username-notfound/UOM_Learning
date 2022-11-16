import sys
from ex3utils import Server
import socket

# Initialise the number of client
num_of_client = 0
names = {}

# Create an class
class MyServer(Server):

	def onStart(self):
		print 'My server has started'

	def onConnect(self, socket):
		# Initialise connection-specific variables
		socket.screenName = None
		print 'An new user connected with the server!'
		global num_of_client
		num_of_client = num_of_client + 1
		print 'There are '+ str(num_of_client) + ' connected with the server'

	def onMessage(self, socket, message):
		# Split incoming message
		(command, sep, parameter) = message.strip().partition(' ')
		# Act upon REGISTER message
		if command == 'REGISTER':
			socket.screenName = parameter
			names[parameter] = socket
		# Act upon MESSAGE message
		if command == 'CHAT':
			socket.message = socket.screenName + ": " + parameter
			for name in names.values():
				if name == names[socket.screenName]:
					pass
				else:
					name.send(socket.message)
		print 'Command is ', command
		print 'Message is ', parameter
		return True

	def onDisconnect(self, socket):
		global num_of_client
		num_of_client = num_of_client - 1
		print 'One user no longer connected with the server!'
		print 'There are '+ str(num_of_client) + ' connected with the server'



# Parse the IP address and port you wish to listen on.
ip = sys.argv[1]
port = int(sys.argv[2])

# Create an echo server.
server = MyServer()

# If you want to be an egomaniac, comment out the above command, and uncomment the
# one below...
#server = EgoServer()

# Start server
server.start(ip, port)
