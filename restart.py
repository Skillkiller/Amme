import os
import platform
import time
import sys

time.sleep(3)

if platform.system() == "Linux":
    os.system("screen -L -S Amme java -jar DiscordBot.jar -restart")
else:
    os.system("java -jar Amaya-1.0-SNAPSHOT.jar")

sys.exit(0)