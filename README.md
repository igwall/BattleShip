# Battleship - Java

## Introduction

> Battleship est un projet de 3eme année (première année de cycle ingénieur) du département Polytech Montpellier. L'objectif est de créer un programme de jeu Battleship en version solo (contre un ordinateur) et duo (deux joueurs humains qui se confrontent). Vous trouverez dans ce document toute les informations sur les classes générées et leurs variables personnelles.

## Les classes de jeu
> Voici l'ensemble des classes de jeu utilisées pour cette version de Battleship : 
### La classe Launcher :


### La classe Joueur :


### La classe Ship :
#### public Ship(char startCoord, char endCoord):
Constructeur de la classe Ship, il permet de créer un nouveau bateau. Le bateaue st caractérisé par ses coordonnées. 
Les coordonnées correspondent à un tableau de coordonée. Une coordonnée correspond à une lettre et un chiffre ex : B3, C3, D3
    
    
    
    
### La classe Coordoonée :
#### private char getX(char coord) :
Cette fontion à pour but de récupérer une chaine de caractère (ex: "B3") et de renvoyer le caractère associé (ici 3).
    
#### private char getY(char coord) :
Cette fontion à pour but de récupérer une chaine de caractère (ex: "B3") et de renvoyer le caractère associé (ici B)    

### La classe Position :

| Attribut      | Type          |
| ------------- |:-------------:|
| emplacement   | Coordonnee[]  | 
| vertical      | boolean       |
| length        | int           |
    

#### public Position(Coordonnee startCoord, Coordonnee endCoord)
Constructeur de l'objet Position. Il prends en considération deux coordonnées pour générer l'ensemble des autres coordonnées. 
ex : B1 -> B4 = B1, B2, B3, B4

#### private Coordonnee[] tableGeneratorH(char x1, char x2, int y)
Génère un tableau dont les coordonnées sont Horizontales
#### private Coordonnee[] tableGeneratorV(int y1, int y2, char x)
Génère un tableau dont les coordonnées sont Verticales

#### public boolean isIn(Coordonnee coord)
Vérifie si la coordonnée entrée en paramètre est présent dans l'ensemble des coordonnées de position. 

#### public boolean allHit()
Si toute les coordonnées dans Position sont touchées (Coordonnée remplacée par "X") renvoie True, sinon, renvoi False.

#### public int getLength()
Renvoi la longeur de la Position (nombre de coordonnées)






### La classe GrilleTir :

### La classe GrilleBateau :

