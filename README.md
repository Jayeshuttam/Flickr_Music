# Flickr_Music





App Screenshots and description:

These screenshots were taken on Asus zenfone Max pro m1 running on Android 11.
SplashScreen.java

i have created the logo from free website https://logomakr.com/
 


LoginActivity:
Called by Splash Screen.
It checks for the user is already in database or not if login is successfull,it will redirect activity to Mainactivity.java
It also has keep me signed in button which saves the user email and password and will login automatically whenever user opens the app until user himself logout  from the app.

 

SignupActivity:
Called when signup button of LoginActivity is clicked.
It stores the user info in sqlite database of android and returns to Login Activity if the signup process is successfull.
It validates the input feilds in tyhe background .It shows error if user has entered invalid input.
 
 

MainActivity.java
This class contains 2 fragments ListFragment which displays the list of songs and Album fragment which displays albums available in local storage .

 
It also a menu in custom action bar which displays user name and a menu  from where user can logout from the app and can check his info by clcicking on My Profile option.

 
MyProfile.java
If we click on the second option of the menu in main activity the it will redirect to MyProfile activity as shown in screenshot below.

 


 
The songs are displayed using custom list and custom list adapter which displays song image,title and a menu button which option to delete a song from local storage.
From the three menu option if we click on Delete option it will pop up a confirmation dialog box to confirm if we want to delete a file or not as shown in below screenshot.


 

when we click on song it will redirect to PlayerAcivity.java
PlayerActivity.java
 
This activity operates as to play music ,seek music to your desired duration,next and previous button also works and works and gives fade in fade out animation in app.
if shuffle button is on it will glow and will shuffle the songs from the  song list.
If repeat button is active the current song will played again and again.
Also the background gradient will also change according to the song image.

Album.java(Second Fragment of activity
 

if we click on any of the album songs corresponding to those album will be shown in another activity shown in below screenshot.
AlbumDetails.java

It also uses custom list and custom   adapter for displaying songs.
 




