[Setup]
AppName=Mojosari Laundry
AppVersion=1.0
DefaultDirName={autopf}\Mojosari Laundry
DefaultGroupName=Mojosari Laundry
OutputDir=C:\Users\HP 14s Ryzen\Documents\NetBeansProjects\Laundryy\AplikasiLaundry\Installer
OutputBaseFilename=Setup_MojosariLaundry_v1.0
Compression=lzma
SolidCompression=yes

[Files]
Source: "C:\Users\HP 14s Ryzen\Documents\NetBeansProjects\Laundryy\AplikasiLaundry\dist\MojosariLaundry.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\HP 14s Ryzen\Documents\NetBeansProjects\Laundryy\AplikasiLaundry\dist\AplikasiLaundry.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\HP 14s Ryzen\Documents\NetBeansProjects\Laundryy\AplikasiLaundry\dist\lib\*"; DestDir: "{app}\lib"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{group}\Mojosari Laundry"; Filename: "{app}\MojosariLaundry.exe"
Name: "{autodesktop}\Mojosari Laundry"; Filename: "{app}\MojosariLaundry.exe"; Tasks: desktopicon

[Tasks]
Name: "desktopicon"; Description: "Create Desktop Icon"; GroupDescription: "Additional Icons:"