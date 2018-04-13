Centrum obs�ugi kart p�atniczych obs�uguje p�atno�ci kartami kredytowymi, debetowymi i bankomatowymi. Z ��daniem autoryzacji ka�dej p�atno�ci mog� zwraca� si� sklepy, zak�ady us�ugowe i firmy transportowe (to s� klienci centrum). Centrum autoryzuje p�atno�� po wys�aniu zapytania do banku kt�ry wyda� dan� kart� p�atnicz� a informacj� o tym archiwizuje w postaci wpisu w swoim systemie bazodanowym, kt�re mo�na przeszukiwa�.
Uwaga: klienci firm s� te� klientami banku i tam maj� konta - nie musz� by� rejestrowani w firmach, p�atno�� odbywa si� (jak w rzeczywisto�ci) podaj�c dane karty i kwot�. S� 2 relacje klient: firma jest klientem centrum, osoba jest klientem firmy i zarazem klientem banku, w kt�rym ma konto.
Minimalny zakres funkcjonalno�ci:
-zarz�dzenie firmami korzystaj�cymi z centrum centrum i bankami (dodawanie, usuwanie, przegl�d)
-zarz�dzanie kartami (przypisana do klienta banku, wydana przez bank)
-obs�uga ��da� autoryzacji (kwota, data, dane karty) - decyzja zale�na od rodzaju karty
-zapis i odczyt stanu systemu na dysk (realizacja archiwum operacji w postaci odr�bnego pliku)
-przeszukiwanie archiwum za pomoc� z�o�onych warunk�w (firma, bank, numer karty, w�a�ciciel, kwota, warunki OR i AND)