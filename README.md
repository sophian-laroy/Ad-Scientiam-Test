# Ad-Scientiam Test

For this test, I used an MVVM approach with Jetpack Compose.

On the first screen, there is a yellow square that can be moved with the finger.
At each change, we store the (x,y) values. When we hit 100 values stored, we saved them in a 
local Room database. That way, we avoid to save in the DB everytime the position changed.

On the second screen, we display the positions and the time when they were reached. We can inverse 
the order of displaying and clear the positions saved in DB.

On the third screen, we can change the theme of the app between: dark, light and the system theme.
