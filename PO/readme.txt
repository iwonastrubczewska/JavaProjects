Centrum obs³ugi kart p³atniczych obs³uguje p³atnoœci kartami kredytowymi, debetowymi i bankomatowymi. Z ¿¹daniem autoryzacji ka¿dej p³atnoœci mog¹ zwracaæ siê sklepy, zak³ady us³ugowe i firmy transportowe (to s¹ klienci centrum). Centrum autoryzuje p³atnoœæ po wys³aniu zapytania do banku który wyda³ dan¹ kartê p³atnicz¹ a informacjê o tym archiwizuje w postaci wpisu w swoim systemie bazodanowym, które mo¿na przeszukiwaæ.
Uwaga: klienci firm s¹ te¿ klientami banku i tam maj¹ konta - nie musz¹ byæ rejestrowani w firmach, p³atnoœæ odbywa siê (jak w rzeczywistoœci) podaj¹c dane karty i kwotê. S¹ 2 relacje klient: firma jest klientem centrum, osoba jest klientem firmy i zarazem klientem banku, w którym ma konto.
Minimalny zakres funkcjonalnoœci:
-zarz¹dzenie firmami korzystaj¹cymi z centrum centrum i bankami (dodawanie, usuwanie, przegl¹d)
-zarz¹dzanie kartami (przypisana do klienta banku, wydana przez bank)
-obs³uga ¿¹dañ autoryzacji (kwota, data, dane karty) - decyzja zale¿na od rodzaju karty
-zapis i odczyt stanu systemu na dysk (realizacja archiwum operacji w postaci odrêbnego pliku)
-przeszukiwanie archiwum za pomoc¹ z³o¿onych warunków (firma, bank, numer karty, w³aœciciel, kwota, warunki OR i AND)