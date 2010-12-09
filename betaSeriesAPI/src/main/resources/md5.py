#!/usr/bin/python

import hashlib
import sys

password = sys.argv[1]

m = hashlib.md5(password)
print m.hexdigest()
