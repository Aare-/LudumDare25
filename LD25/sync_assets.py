import os

sauce_prefix = "J:\\compo\\Ludum Dare\\LD25\\LD25\\assets\\"
sauce = ["fonts",
		 "textures",
		 "bodies"]
targets = ["J:\\compo\\Ludum Dare\\LD25\\LD25\\LD25-android\\assets\\",
		   "J:\\compo\\Ludum Dare\\LD25\\LD25\\LD25-desktop\\assets\\"]

for s in sauce:
	for t in targets:
		os.system('rmdir "'+t+'\\'+s+'" /s /q')
		os.system('xcopy "'+sauce_prefix+s+'" "'+t+'\\'+s+'" /q /y /e /i /h')

