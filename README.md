<div align="center">
  <h1 align="center">
        Paint Clone
    <img src="MDImages/001-paint.png" />
  </h1>

  <h3 align="center">A reproduction of the windows paint application.</h3>
</div>

<br/>

![screenshot](MDImages/screenshot.png)


## Project Purpose

The IT world is having a huge impact on all sectors today. The field of art has not been left out of this evolution. So, to support my JAVA development skills I have reproduced from scratch the Ms Paint application.

## UX

### Strategy

App Objective: Provide a platform for artist allowing them to draw and record their creation.

User Needs: Draw, erase, save, copy/paste and use color palette. 

Opportunities/Problems table used to determine the strategic priorities UX efforts should address (in this order):

| Opportunity/Problem                                                 | Importance | Viability/Feasibility |
| :------------------------------------------------------------------ | :--------: | :-------------------: |
| A. Have a UI as simple as Ms Paint                                  |     5      |           5           |
| B. Provide color picker, color palette and color whell              |     5      |           5           |
| C. Edit workspace (ruler, gridlines, statusbar, etc)                |     4      |           5           |
| D. Allow copy/paste of drawings                                     |     2      |           4           |
| E. Resizable window                                                 |     2      |           2           |

### Scope

#### Functionnal Specifications

In reviewing the functional specifications, I made observations about existing drawing applications, including the Microsoft app, [Photoshop](https://www.adobe.com/fr/products/photoshop.html), [GIMP](https://www.gimp.org/) and Android apps such as [Adobe Draw](https://play.google.com/store/apps/details?id=com.adobe.creativeapps.draw&hl=fr&gl=US) and [Ibis Paint](https://play.google.com/store/apps/details?id=jp.ne.ibis.ibispaintx.app&hl=fr&gl=US). This helped me to identify the key features that users of drawing apps expect to see.

Feature set:
- A canva that serves as a paper to display the artist's design.
- An infinite number of accessible colours and a colour palette customizable.
- Tools that improve the quality of the design (ruler, gridlines, statusbar, etc).
- Status bar that provides additional information about the drawing.
- Save/import files.
- Zoom, flip and resize the Canva.
- Possibility to write text.

### Structure

#### Interaction Design

Consistency & Predictability:

- Common interface.
- Sober and clear, but consistent colours to highlight the different tools and make the user's design stand out.
- Separate the application space (options, tools), from the user space (the canva) in two.


Feedback:

All interactive elements provide feedback to the user to encourage interaction and provide confirmation when actions are taken.

- The mouse changes shape when a tool such as the pencil is activated.
- Tools are highlighted on hover.
- The drop-down menu are smooth animated.
- Each selected color is highlighted on a bubble.
- A tooltip appears after a certain time of hovering over a tool.

### Surface

App's Colors: 

- LIGHT : WHITE `#ffffff`, LIGHTGRAY `#c3c3c3`, LIGHTYELLOW `#efe4b0`, LIGHTTURQUOISE `#99d9ea`, LIGHTGOLD `#ffc90e40`.
- DARK: BLACK `#333333`, DARKRED `#880015`, DARKWHITE `#fafafa`.
- PALE: PALEGRAY `#f9f1ee`, `#f6f6f6`.
- SMOOTH: SMOOTHGRAY `#f8f8f8`.
- FONT: `#222222`.
- TRANSPARENT: TRANSPARENT `#ffffff11`, TRANSPARENTGRAY `#cccccc40`.

App's Font: `Century Gothic`.

### Existing Features

1. The user can draw free forms and view the result in real time.
2. The user is supported by common shapes.
3. Shape filling respects the boundaries of its path.
4. Brush and pencil are resizable.
5. Change color brush/pencil.
6. Make custom color.
7. Erase any pixel on the canva.
8. Pick the color of any pixel on the canva.
9. Undo button and shortcut.
10. Select shape objects in the canva.
11. Save the document.
12. Change the display of the Canva (rotate, zoom in/out, flip, etc).

### Features to add

| Label      | Description |
|:-----------|:------------|
| Quick Fill | consists in making a long click in an area and the area will be filled with the last active colour. This makes colouring faster and eliminates the need to change tools to fill the area. |
| Hide/Show Status bar | To increase the user's field of view on their drawing space |
| Move a shape | Move a (custom) shape around the canva |
| Edit a shape | Edit a (custom) shape on the canva |

## Technologies Used

- [Java](https://fr.wikipedia.org/wiki/Java_(langage))
- [Slick2D](https://slick.ninjacave.com/javadoc/) : An easy to use set of tools and utilites wrapped around LWJGL OpenGL bindings to make 2D Java game development easier. See the documentation for more information.
- [Eclipse IDE](https://www.eclipse.org) : My code editor in Java.


## Testing 

Extensive manual testing has been carried out to ensure that the application works and displays correctly on any Windows computer.

The following tests passed: 

| Test No. | Test Name                                        | Result |
|:---------|:-------------------------------------------------|:-------|
|    1     |  Simple design inspired by Ms Paint              |  PASS  |
|    2     |  Good operation of shorcuts                      |  PASS  |
|    3     |  Extensible and scrollable workspace             |  PASS  |
|    4     |  Stable & speed application                      |  PASS  |
|    5     |  Exit without save                               |  PASS  |
|    6     |  Image file import                               |  PASS  |
|    7     |  Save file in common image type of file          |  PASS  |
|    8     |  Can be used in Windows 8, 8.1, 10 and 11        |  PASS  |

The following tests failed:


| Issue No. | Test Name                                        | Result |  Issue                                                                                 |
|:----------|:-------------------------------------------------|:-------|:---------------------------------------------------------------------------------------|
|     1     |  Energy efficient application                    |  FAIL  | The application uses up to 20% of the processor capacity                               |
|     2     |  Same render in all Windows computer             |  FAIL  | Some white pixels appear on the drawing on Full HD sceerns and higher                  |
|     3     |  Resizable application                           |  FAIL  | The higher the quality of the computer screen, the smaller the size of the application |


## Deployment 

### Creation of the JAR

I use Eclipse. So I had to proceed as follows:

1. Right click on the project > Click on `export` > `JAR file`.
2. Choose the resources you want to put in the JAR.
3. Choose the options you want >  Enter the destination address of your JAR > Click on `next`.
4. Click on next >  Enter the class containing the main.
5. Click on `finish`.


### Creation of the app launcher (.exe)

A launcher is simply a native program that will run your own program. You must therefore have already created a .jar before creating a launcher.

To convert my .jar file to a .exe file, I used the online converter : [convertio.co](https://convertio.co/fr/convertisseur-jar/).


## Credits

### Images 

All images and icons sourced from [FlatIcon](https://www.flaticon.com/) (Standard License)


### Acknowledgements

Big thanks to my friends and family for help with testing and feedback.
