# Simple ASP solver

This project is a very basic ASP (answer set programming) solver written in `Java` developed during the AI lecture on summer 2015 at UniKo.

It takes a grounded logical program and computes the answer sets by simply calculating the powerset of the set of all head literals. And for each enty (e.g. state) of this powerset, the program tests if the minmal model of the reduct of the initial program is equal to the state. If so, a solution is found.

The project is a `NetBeans 8` project and can be opened directly with `NetBeans`.

# Example
Consider the following example (from AI lecuture, slide 272, [1]):

	vogel(tweety) <- pinguin(tweety).
	fliegt(tweety) <- vogel(tweety), not ¬fliegt(tweety).
	¬fliegt(tweety) <- pinguin(tweety).
	fliegt(tweety) <- fledermaus(tweety).
	<- vogel(tweety), fledermaus(tweety).
	pinguin(tweety).
	vogel(batman) <- pinguin(batman).
	fliegt(batman) <- vogel(batman), not ¬fliegt(batman).
	¬fliegt(batman) <- pinguin(batman).
	fliegt(batman) <- fledermaus(batman).
	<- vogel(batman), fledermaus(batman).
	fledermaus(batman).

The ASP solver answers with:

	Minimal model = [vogel(tweety), ¬fliegt(tweety), pinguin(tweety), fliegt(batman), fledermaus(batman)]

So: Tweety is a bird, can't fly and is a penguin. Batman is a bat and can fly (where `fliegt = fly`, `vogel = bird`, `pinguin = penguin` and `fledermaus = bat`).

# License
License: creative commons 4.0, by-sa

`This program is distributed WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.`

# Links

[1] http://west.uni-koblenz.de/de/studium/lehrveranstaltungen/ss15/kuenstliche-intelligenz