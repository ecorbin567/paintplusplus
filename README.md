# Paint++
### Authors: Elise Corbin, Victor Jiang, Lauren Park, Josh Villas, Oscar Yasunaga

Paint++ is a modern reimagining of the classic Microsoft application Paint, in which users
can draw on a canvas, as well as import and modify images. With Paint++, users can draw their
own design using images from their computer or a blank canvas, and then save the resulting image
to their computer. We recommend Paint++ as a free, functional, and useful tool for artists, 
professionals or students looking to brainstorm ideas, Photoshop users, or regular people with 
creative hobbies.

## Table of Contents
1. [Software Features](#software-features)
2. [Running Paint++](#running-paint)
3. [Using Paint++](#using-paint)
4. [Licence](#licence)
5. [Submit Feedback](#submit-feedback)
6. [Contribute](#contribute)

## Software Features

### Use Cases
Paint++ uses Clean Architecture and follows the SOLID Design Principles for the following use cases:
- Draw and erase (developed by Elise)
- Undo and redo (developed by Elise)
- Login and signup (developed by Josh)
- Accessing past canvases and storing new ones (developed by Josh)
- Import, resize, crop, and rotate images (developed by Lauren)
- Save canvas to your computer (developed by Oscar)
- Resize canvas (developed by Oscar)
- Zoom in and out (developed by Oscar)
- Change the size and colour of the paintbrush (developed by Victor)
- Box select and drag sections of the canvas (developed by Victor)

### Supabase API
Paint++ has a signup and login system supported by Supabase API, but you can also run Paint++
on your own device's memory. See [Running Paint++](#running-paint) for more details. In addition,
Supabase API also supports storing each user's past canvases.

## Running Paint++
To launch Paint++, run the main method in src/main/java/app/Main.java.
If you want to use your own device's memory rather than the Supabase API so that your login
information is not stored, run the main method in src/main/java/app/MainInMemory.java.

## Using Paint++
When you open the application, you will see a signup screen, where you
can create an account if you don't have one. If you already have an account, click the "Go To
Login" button.

Once you are logged in, you will be able to view and edit any past canvases you have worked
on, as well as create a new blank canvas. Within each canvas, you can draw a brushstroke of any
size and colour, erase with any size, box select, and import, resize, crop, and rotate .png images
from your computer.

The menu at the top of each canvas allows you to resize the entire canvas, zoom in and out,
undo and redo actions, save the canvas to your computer, return back to the My Canvases page,
and log out. All top menu actions correspond to keyboard shortcuts as well for additional flexibility.

## Licence
Paint++ has an MIT Licence, which is available in LICENSE.txt.

## Submit Feedback
We welcome feedback on Paint++! To submit feedback, please open an issue on the GitHub repository:
https://github.com/ecorbin567/paintplusplus/issues

## Contribute
We welcome contributions to Paint++! To add new features or change existing code, feel free to fork
our GitHub repository and then start a pull request: https://github.com/ecorbin567/paintplusplus/pulls
