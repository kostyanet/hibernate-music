#!/bin/bash
#Being started from the root of hibernate-project it should read the content of your packages
#It should analyze the name of every java-file in your project
#Script must ask the snide question to the user how he would like to process files.
#If the length of a certain file will be greater than count characters, read from the previous step,  then script must create the directory (you can select any proper name) and put the name of this file in a plain text file with any name which you would like to assign to him.
#Also script must process files which are not corresponding to condition designed above, in the proper way.

currentDir=$(pwd);
targetDirName="hw27result";
shortNamesFile="shorts.txt";
longNamesFile="longs.txt";
longNamesFilePath=$targetDirName/$longNamesFile;
shortNamesFilePath=$targetDirName/$shortNamesFile;

# Ask user for a threshold of file name.
echo "Please specify file name threshold value: "
read threshold

# Check the output directory and files
if [ -d $targetDirName ]; then
  rm -rf $targetDirName;
fi
if [ -f $longNamesFilePath ]; then
  rm $longNamesFilePath;
fi
if [ -f $shortNamesFilePath ]; then
  rm $shortNamesFilePath;
fi
mkdir $targetDirName;
touch $longNamesFilePath;
touch $shortNamesFilePath;

# Function declarations
browseDir() {
  for x in "$1"/*; do
    if [ -d "$x" ]; then
      browseDir "$x"
    elif [ -f "$x" ]; then
      handleFile "$x"
    fi
  done
}

handleFile() {
  if [[ $1 == *.java ]]; then
    checkLengthAndWrite $(basename "$1" .java) $(basename -- "$1");
  fi
}

checkLengthAndWrite() {
  if [ ${#1} -ge $threshold ]; then
    echo $2 >> $longNamesFilePath;
  else
    echo $2 >> $shortNamesFilePath;
  fi
}

# Run main function.
browseDir $currentDir;
