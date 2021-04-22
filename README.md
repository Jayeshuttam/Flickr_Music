
# Flickr_Music





App Screenshots and description:

These screenshots were taken on Asus zenfone Max pro m1 running on Android 11.
SplashScreen.java


<img src="https://user-images.githubusercontent.com/38659267/115761350-425d2f80-a370-11eb-9c14-c835be83f579.png" width="350" height="500">

i have created the logo from free website https://logomakr.com/
 


LoginActivity:
Called by Splash Screen.
It checks for the user is already in database or not if login is successfull,it will redirect activity to Mainactivity.java
It also has keep me signed in button which saves the user email and password and will login automatically whenever user opens the app until user himself logout  from the app.


<img src="https://user-images.githubusercontent.com/38659267/115761404-4c7f2e00-a370-11eb-8903-6f5426b085af.png" width="350" height="500">

 

SignupActivity:
Called when signup button of LoginActivity is clicked.
It stores the user info in sqlite database of android and returns to Login Activity if the signup process is successfull.
It validates the input feilds in tyhe background .It shows error if user has entered invalid input.
 
 
 
<img src="https://user-images.githubusercontent.com/38659267/115761446-56a12c80-a370-11eb-8f91-c9f34dade515.png" width="350" height="500">


MainActivity.java
This class contains 2 fragments ListFragment which displays the list of songs and Album fragment which displays albums available in local storage .


<img src="https://user-images.githubusercontent.com/38659267/115761470-5b65e080-a370-11eb-96ff-3b0fef5c7f3d.png"  width="350" height="500">

 
<img src="https://user-images.githubusercontent.com/38659267/115761484-5f91fe00-a370-11eb-8ac6-b2bff080623d.png"  width="350" height="500">
 
It also a menu in custom action bar which displays user name and a menu  from where user can logout from the app and can check his info by clcicking on My Profile option.

 
MyProfile.java
If we click on the second option of the menu in main activity the it will redirect to MyProfile activity as shown in screenshot below.


<img src="https://user-images.githubusercontent.com/38659267/115761507-6751a280-a370-11eb-84b3-49a4a60ecd71.png"  width="350" height="500">

<img src="https://user-images.githubusercontent.com/38659267/115762006-fb236e80-a370-11eb-8344-431042728cc2.png"  width="350" height="500">

 


 
The songs are displayed using custom list and custom list adapter which displays song image,title and a menu button which option to delete a song from local storage.
From the three menu option if we click on Delete option it will pop up a confirmation dialog box to confirm if we want to delete a file or not as shown in below screenshot.


 

when we click on song it will redirect to PlayerAcivity.java
PlayerActivity.java


<img src="https://user-images.githubusercontent.com/38659267/115762021-0080b900-a371-11eb-8abb-dd29e0a5c2cf.png" width="300" height="500">
<img src="https://user-images.githubusercontent.com/38659267/115762077-0f676b80-a371-11eb-9c0d-7492b67549f9.png" width="300" height="500">


 
This activity operates as to play music ,seek music to your desired duration,next and previous button also works and works and gives fade in fade out animation in app.
if shuffle button is on it will glow and will shuffle the songs from the  song list.
If repeat button is active the current song will played again and again.
Also the background gradient will also change according to the song image.

Album.java(Second Fragment of activity
 
![Uploading Album_main.png…]()
<img src="https://user-images.githubusercontent.com/38659267/115762021-0080b900-a371-11eb-8abb-dd29e0a5c2cf.png" width="350" height="500">

if we click on any of the album songs corresponding to those album will be shown in another activity shown in below screenshot.
AlbumDetails.java

<img src="https://user-images.githubusercontent.com/38659267/115762125-1ee6b480-a371-11eb-8c02-4b7747829d97.png" width="350" height="500">


It also uses custom list and custom   adapter for displaying songs.
 

Common Setup:
You can run the project with 2 ways:

First Method

1.Download the Zip
2.Extract the zip to you desired location
3.Open Android studio
4.File->Open Project->project location
5.You are good to go


Second Method

1.Copy the following link : https://github.com/Jayeshuttam/Flickr_Music

2.Open Android Studio

3. File->New Project->Project from Version Control->In Url Tab Paste the above link->Click on Clone.

4.You are Good to go.



