variables
	Uno dos TRES
fin_variables

comienza
	Uno<<3
	dos<<20
	TRES<<2
	repite
	  Uno<<dos-Uno
	  si(Uno>15)entonces
	    Uno<<dos-Uno+1
	  finsi
	hasta(Uno>=7)
	Uno<<Uno+1
termina
