#!/usr/local/bin/python
import os
rootdir="./spg"
for parent,dirnames,filenames in os.walk(rootdir):
    for filename in filenames:
        print filename
        os.system('java -cp bin/ spiglet.spiglet2kanga.Main < ./spg/'+filename+' > res.kg')
        os.system('java -jar kgi.jar < res.kg > res1')
        os.system('java -jar pgi.jar < ./spg/'+filename+' > res2')
        os.system('diff res1 res2')
