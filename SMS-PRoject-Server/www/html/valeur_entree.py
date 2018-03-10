#!/usr/bin/python3
# -*-coding:Utf-8 -*
"""
Ce programme permet de créer une interface pop afin
d'entrer l'identifiant et le mot de passe unice pour
la fonction "formulaire"
"""
from tkinter import Tk, Button, Entry

contenu_de_entrer = ''

def val_num():
    """
    La fonction qui crée la fenêtre
    Args:
        Aucun
    Returns:
        contenu_de_entrer (str): chaine contenant l'identifiant unice et le mot de passe,
        séparer par un caractère espace
    """

    #Creation de l'interface

    fenetre_tkinter_input = Tk()
    fenetre_tkinter_input.title("Formulaire : Utilisateur et Mot de passe Unice")

    fenetre_tkinter_input.geometry('500x100+500+400')
    fenetre_tkinter_input.configure(background="white")

    def return_entry(event=""):
        """
        Permet de retourner les valeurs entré lorsqu'elle est appelée
        Args:
            Rien
        Returns:
            Rien (Actualise la valeur contenu_de_entrer, puis quitte la fenêtre)
        """
        global contenu_de_entrer
        contenu_de_entrer = str(entry.get())+' '+str(entry_mdp.get())
        fenetre_tkinter_input.quit()

    entry = Entry(fenetre_tkinter_input)
    entry.bind("<Return>", return_entry)
    entry.pack()
    entry.focus_set()
    #entre.bind avec "<Return>" permet d'utiliser la
    #touche "Entrer" du clavier afin de simplifier la saisie

    entry_mdp = Entry(fenetre_tkinter_input, show="*")
    entry_mdp.bind("<Return>", return_entry)
    entry_mdp.pack()


    bouton_input = Button(
        fenetre_tkinter_input,
        text="Appuyer sur Entrer",
        command=lambda: entry.bind(return_entry())
        )
    bouton_input.pack()
    #Le bouton_input permer d'appeler la fonction return_entry
    #lorsque les champs on été saisie d'après l'utilisateur

    fenetre_tkinter_input.mainloop()
    fenetre_tkinter_input.destroy()
    #Fermeture de la fenêtre après l'avoir quitter

    return contenu_de_entrer
