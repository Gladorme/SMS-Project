#!/usr/bin/python3
# -*-coding:Utf-8 -*

from tkinter import *
from tkinter.ttk import *

class App:

    def __init__(self, parent):
        self.parent = parent
        self.combo()
        self.button(text="lol")

    def newselection(self, event):
        self.value_of_combo = self.box.get()
        print(self.value_of_combo)

    def combo(self):
        self.box_value = StringVar()
        self.box = Combobox(self.parent, textvariable=self.box_value)
        self.box.bind("<<ComboboxSelected>>", self.newselection)
        self.box['values'] = ('X', 'Y', 'Z')
        self.box.pack()

    def button(self, text):
        self.but = Button(self.parent, text=text, command=lambda: self.combo.bind("<<ComboboxSelected>>", self.parent.combo.newselection))
        self.but.pack()
 
if __name__ == '__main__':
    fenetre_tkinter = Tk()
    fenetre_tkinter.title("Gestionnaire de la Blackliste")
    fenetre_tkinter.geometry('800x640+300+0')
    fenetre_tkinter.configure(background="white")
    app = App(fenetre_tkinter)
    fenetre_tkinter.mainloop()