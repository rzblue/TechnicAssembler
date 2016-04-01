TechnicAssembler
================

A simple utility to assemble modpacks into the Technic Launcher modpack format. This app is free to use and is licensed by the GNU General Public License v3.0.

It is based off of [TehNut's](https://github.com/TehNut) [Assembler](https://github.com/TehNut/Assembler).

##Arguments

* `-d`: This is the input directory of your pack. You can either use an absolute path (All the way from your drive to the directory) or add onto the directory that you opened your terminal in. Defaults to current directory.
* `-o`: This is the name of your modpack. This can be almost anything without spaces. Defaults to "Modpack"
* `-v`: This is the version of your modpack. Again, almost anything without spaces. Defaults to "0.0.1"
* `-client`: Builds the client zip. Does not have a modifier. Defaults to false.
* `-server`: Builds the server zip. Does not have a modifier. Defaults to false.

##File Structure

```
├── bin
│   ├── client  Jar modification files
│   └── server  Server jars, Forge libs, and start scripts
├── config
│   ├── client  Client only config files
│   ├── common  Shared config files
│   └── server  Server only config files
├── extra       Extra stuff, such as readme's and licenses
│   ├── client  
│   ├── common
│   └── server
└── mods        Jars that should be placed in "mods" folder
    ├── client  Client only mods
    ├── common  Shared mods
    └── server  Server only mods
```

Most of your mods will go into mods/common.
