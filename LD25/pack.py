import os

#----CONFIG----
PACKER_PATCH = "J:\\Workspace\\LibTools\\packer2.4.1\\pack.jar"
SCALER_PATCH = "J:\\Programy\\IM\\convert.exe"
FILTRANS = "J:\\Dropbox\\Private\\Filtrans\\Filltrans.exe";
DUPA = "J:\\dupa.txt"
#--------------

def scale(input, output, percent):
	dir = os.listdir(input)
	i = 0.0
	print("----Scalling----");
	for d in dir:
		print("    Scaling img from folder "+d+":")
		for img in os.listdir(input+d):
			if img[-3:] == 'png' or img[-3:] == 'jpg':
				#os.system('del '+output+d+'\\'+del_d)
				s = SCALER_PATCH+" -resize \""+percent+"%"+"\" \""+input+d+"\\"+img+"\" \""+output+d+"\\"+img+"\""
				os.system(s)
	print("----Finished----")
	
def pack(input, output):
	print("----Packing----")
	os.system('del /s /q "'+output+'*" > '+DUPA)
	os.system('del '+DUPA)
	for d in os.listdir(input):
		os.system(PACKER_PATCH+' --silent --project="'+input+d+"\\"+d+'.prj"')
		os.system('rename "'+output+d+'\\pack" "'+d+'"')
		#FILTRANS
		os.system(FILTRANS+' '+output+d+'\\'+d+'1.png')

scale("J:\\compo\\Ludum Dare\\LD25\\LD25\\art\\raw\\", "J:\\compo\\Ludum Dare\\LD25\\LD25\\art\\scaled\\", "100")
pack("J:\\compo\\Ludum Dare\\LD25\\LD25\\art\\scaled\\", "J:\\compo\\Ludum Dare\\LD25\\LD25\\assets\\textures\\");
#SYNCING ASSETS
os.system('"J:\\compo\\Ludum Dare\\LD25\\LD25\\sync_assets.py"')