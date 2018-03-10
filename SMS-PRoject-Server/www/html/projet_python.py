#!/usr/bin/python3
# -*-coding:Utf-8 -*

"""
Ce fichier et le fichier principal,
il permet de lancer l'interface graphique
"""

import sys
import os
from tkinter import Tk, PhotoImage, Frame, Canvas, Button
from tkinter.messagebox import askretrycancel
from tkinter.filedialog import askopenfilename
from tkinter.ttk import *

from valeur_entree import val_num

def interface(x_fenetre=800, y_fenetre=640):
    """
    La fonction interface permet de créer une interface,
    Ce sera l'interface principal ou il suffira de cliquer
    sur un bouton pour appeler les différents fonctions du projet
    Args:
        Il est possible lors de l'éxecution du programme,
        de rentrer x_fenetre et y_fenetre via la méthode sys.argv
        x_fenetre (int): Longueur de la fenêtre
        y_fenetre (int): Hauteur de la fenêtre
    """

    #On crée l'interface avec sa taille, son icône, sa couleur de fond...
    #fenetre_tkinter est l'objet interface principal du programme
    fenetre_tkinter = Tk()
    fenetre_tkinter.title("Gestionnaire de la Blackliste")
    fenetre_tkinter.geometry(str(x_fenetre)+'x'+str(y_fenetre)+'+300+0')
    fenetre_tkinter.configure(background="white")


    #On crée un frame (partie d'une fênetre, voir documentation Tkinter)
    frame_liste_num = Frame(
        fenetre_tkinter,
        width=x_fenetre/2,
        height=y_fenetre-10,
        )

   
    #Position du frame
    frame_liste_num.place(x=10, y=10)


    combobox = Combobox(
        frame_liste_num,
        width=45,
        height=int(y_fenetre-20),
        background="white",
        values = ["0000000000", "0933909398"],
        state = "normal"
        )


    combobox.bind('<<ComboboxSelected>>', print(Combobox.get()))
    combobox.pack()

    #Le canvas nous permet de dessiner l'image dans le frame
    #à une certaine position
    frame_bouton = Frame(
        fenetre_tkinter,
        width=x_fenetre/2-20,
        height=y_fenetre-20
        )
    #On crée un frame où l'on va insérer des boutons appelants
    #les différentes fonctions du programme
    frame_bouton.place(x=x_fenetre/2+10, y=10)

    canvas_bouton = Canvas(
        frame_bouton,
        width=x_fenetre/2-20,
        height=y_fenetre-20, background="white"
        )
    #Le canvas_bouton nous permettra de dessiner ces différents
    #bouton
    bouton_formulaire = Button(
        canvas_bouton,
        text="Récuperer une page avec un formulaire",
        command=lambda: formulaire(
            valeur_entree_formulaire()
            ),
        #command est l'option du bouton qui exécute l'appel
        #de la fonction liée
        #On passe par une fonction lamba afin de pouvoir entrer
        #des paramètres sans que la fonction soit exécute au lancement
        #de projet_python.py
        width=int(x_fenetre/32+5)
        )

    bouton_correcteur = Button(
        canvas_bouton,
        text="Corriger une page HTML",
        command=lambda: correcteur_de_html_css(
            chercher_fichier('html')
            ),
        width=int(x_fenetre/32+5)
        )

    #On pointe tous les boutons dans une liste
    liste_bouton = []
    liste_bouton.append(bouton_formulaire)
    liste_bouton.append(bouton_correcteur)


    i = (y_fenetre/4)-70

    #On parcours la liste et on dessine les boutons
    #afin de les centrer dans le canvas
    for bouton in liste_bouton:
        bouton.pack()
        canvas_bouton.create_window(x_fenetre/4-10, i, window=bouton)
        i += 60


    canvas_bouton.pack()

    #fenetre_tkinter.mainloop() et la fonction
    #permettant d'utiliser l'interface
    fenetre_tkinter.mainloop()
    fenetre_tkinter.quit()

interface()