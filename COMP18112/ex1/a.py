#!/usr/bin/env python

import urllib

url = "http://studentnet.cs.manchester.ac.uk/ugt/COMP18112/page3.html"
data = urllib.urlopen(url)
tokens = data.read().split()
print tokens
