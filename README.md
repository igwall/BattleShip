# Battleship - Java (1.0.1)

## Introduction

> Battleship est un projet de 3eme année (première année de cycle ingénieur) du département Polytech Montpellier. L'objectif est de créer un programme de jeu Battleship en version solo (contre un ordinateur) et duo (deux joueurs humains qui se confrontent). Vous trouverez dans ce document toute les informations sur les classes générées et leurs variables personnelles.

## Les classes de jeu
> Voici l'ensemble des classes de jeu utilisées pour cette version de Battleship : 


### La classe Launcher :
> La classe launcher initialise l'ensemble du jeu. Vous pouvez décider de jouer en solo ou en duo. si vous jouez en duo, les deux joueurs créés sont des humains, sinon, un joueur sera humain (vous) l'autre sera un bot. (Il y aura surement une version bot vs bot dans la version finale)

***
### La classe Duo :
|Securité | Attribut      | Type       |
|:-------:| ------------- |:----------:|
| private | joueur1       | Human      | 
| private | joueur2       | Human      |
| private | nbShip        | int        |
    

#### public Duo(String namePlayer1, String namePlayer2)
> Constructeur de la classe qui initialise deux joueurs avec leurs prenoms.

#### public void newGame()
> Fonction qui lance une nouvelle partie. Les deux joueurs vont alors s'affronter. 


### La classe Solo :
> En cours de finission, le bot doit pouvoir tirer et s'adapter, ce qui est en cours :) 






***
### La classe Solo : 
>En cours


*** 
### La classe Player :
    

|Securité | Attribut      | Type          |
|:-------:| ------------- |:-------------:|
| protected | capacity[]  | int           | 
| protected | score       | int           |
| protected | army        | <Ship>        |
| protected | name        | String        |
| protected | grilleBateau| GrilleBateau  |
| protected | grilleTir   | GrilleTir     |



#### public Player(String name)
> Construit un joueur avec un nom.

#### public void shipGenerator(int current)
> Génère un tableau et l'ajoute à son armée (army)

#### public boolean isHit(Coordonnee coord)
> Fonction qui parcourre l'ensemble des bateaux du joueur et regarde si le bateau est touché

#### private boolean shipLengthControl(int length)
> Controle la longueur du bateau entré en paramètre

#### public int getCountDestroyed()
> Renvoi le nombre de bateau détruits du joueur

#### public int getScore()
> Renvoi le score du joueur

#### public void displayInfos()
> Renvoi le score et les grilles du joueur lors de la partie pour qu'il puisse jouer.

#### public void addHit(Coordonnee coord)
> Fonction d'envoi à la grille pour que la coordonnée soit marquée comme touchée.

#### public void addMiss(Coordonnee coord)
> Fonction d'envoi à la grille pour que la coordonnée soit marquée comme raté.

#### public String getName()
> Renvoi le nom du joueur.


#### public void setScore(int score)
> Modifie le score du joueur



***
### La classe Human
> Son constructeur hérite de player, pour voir ses attributs, voir ceux de la classe Player

#### public Coordonnee getTir()
> Fonction qui renvoi la coordonnée généré par la saisie de l'utilisateur. Cette fonction sera différente pour le Bot car elle demande un traitement d'analyse et de saisie particulier.  
        
***
### La classe Human :
> En cours de création

***
### La classe Ship :
#### public Ship(char startCoord, char endCoord):
> Constructeur de la classe Ship, il permet de créer un nouveau bateau. Le bateaue st caractérisé par ses coordonnées. 
Les coordonnées correspondent à un tableau de coordonée. Une coordonnée correspond à une lettre et un chiffre ex : B3, C3, D3
    
    
    
    
    
    
    
    
    
***
### La classe Coordoonée :


|Securité | Attribut      | Type          |
|:-------:| ------------- |:-------------:|
| private | x             | char          | 
| private | y             | int           |
| private | coord         | String        |


#### private char getX(char coord) :
> Cette fontion à pour but de récupérer une chaine de caractère (ex: "B3") et de renvoyer le caractère associé (ici 3).

#### private char getY(char coord) :
> Cette fontion à pour but de récupérer une chaine de caractère (ex: "B3") et de renvoyer le caractère associé (ici B)

#### public Coordonnee(String coord)
> Constructeur de la classe

#### private int calcY(String coord)
>Décompose la coordonnée String afin d'en récupérer le chiffre.

#### private char calcX(String coord)
> Décompose la coordonnée String afin d'en récupérer la lettre.

#### public void setHit()
> Cette fonction transforme la valeur de la coordonnée pour "X"

#### public boolean isHit()
> Renvoi true si la coordonnée est touché (value = "X")
  Renvoi false sinon.

#### public String getValue()
> Renvoi la valeur "brute" de la coordonnée (son String)
     
     
     
     
     
     
     
     
     
***
 ### La classe Position :

|Securité | Attribut      | Type          |
|:-------:| ------------- |:-------------:|
| private | emplacement   | Coordonnee[]  | 
| private | vertical      | boolean       |
| private | length        | int           |


 #### public Position(Coordonnee startCoord, Coordonnee endCoord)
 > Constructeur de l'objet Position. Il prends en considération deux coordonnées pour générer l'ensemble des autres coordonnées. 
 ex : B1 -> B4 = B1, B2, B3, B4

#### private Coordonnee[] tableGeneratorH(char x1, char x2, int y)
> Génère un tableau dont les coordonnées sont Horizontales

#### private Coordonnee[] tableGeneratorV(int y1, int y2, char x)
> Génère un tableau dont les coordonnées sont Verticales

#### public boolean isIn(Coordonnee coord)
> Vérifie si la coordonnée entrée en paramètre est présent dans l'ensemble des coordonnées de position. 

#### public boolean allHit()
> Si toute les coordonnées dans Position sont touchées (Coordonnée remplacée par "X") renvoie True, sinon, renvoi False.

#### public int getLength()
> Renvoi la longeur de la Position (nombre de coordonnées)










***
### La classe GrilleTir :
> Classe enfant de la classe Grille.

#### public GrilleTir()
> Créer une matrice de 10 x 10 cases

#### public void affichageGrilleTir()
> Affiche la grille qui sauvegarde les tirs effectués

#### public void addHit(Coordonnee coord)
> Rajoute la coordonnée entrée en paramètre comme étant touchée dans la grille du joueur. 

#### public void miss(Coordonnee coord)
> Rajoute la coordonnée entrée en paramètre comme étant ratée dans la grille du joueur. 











*** 
### La classe GrilleBateau :
> Classe enfant de la classe Grille.

#### public GrilleBateau()
> Créer une matrice de 10 x 10 cases

#### public boolean positionValide(Position position)
> Vérifie que l'ensemble des coordonnée de la position entrée en paramètre est disponible. Si un des bateaux à déjà "marqué sa position" sur la matrice, elle renvoi faux, sinon, elle renvoi true. Permet d'éviter les chevauchements de bateau

#### public boolean getOccupeCoord(Coordonnee coord)
> Vérifie si la coordonnée entrée en paramètre est bien dans la matrice (pas de dépassement).

#### public void updateGrille(Position position)
> Bloque l'ensemble des Coordonnées de position afin qu'elles ne soient plus utilisée par un autre bateau.

#### public void updatePrintHit(Coordonnee coord)
> Si le joueur adverse tire sur le bateau, la coordonnée du bateau touché est marqué afin d'afficher un "X".

#### public void affichageGrilleBateau()
> Affiche la grille et la position des différents bateaux/








***
### La classe Grille:
> Classe parente des grilles GrilleBateau et Grille Tir. Les deux grilles ont les mêmes paramètres, seul leur traitement diffère. 

|Securité           | Attribut      | Type          |
|:-------:          | ------------- |:-------------:|
| protected static  | size          | int           | 
| protected         | grille[]      | int[]         |



#### public Grille()
> Constructeur par défaut d'une grille. Il créé une matrice de 10 x 10

#### public Grille(int size)
> Constructeur spécifique si l'on souhaite modifier la taille du plateau de jeu

#### protected int coordConverter(char x)
> Récupère La valeur X de la coordonnée pour pouvoir la traitée sur la matrice.

#### public boolean coordControl(Coordonnee coord)
> Vérifie que la coordonnée entrée est dans la matrice. 

***

## Hiérarchie et génération (schéma) : 

    Launcher
    |
    -Solo
    |
    - Duo -- (1) Human
        | -- (2) Human -- Score
                    |  --- Capacity
                    |  --- Army - Ship
                    |         | - Ship - Position - Coordonnée[]
                    |         | - Ship
                    |         | - Ship
                    |         | - Ship
                    |
                    |  --- Name
                    |  --- GrilleTir
                    |  --- GrilleBateau

