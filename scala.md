# PROGRAMMAZIONE FUNZIONALE - SCALA

## Paradigmi di programmazione

### Programmazione imperativa (IP)
Pardigma di programmazione secondo il quale un programma viene inteso come un insieme di istruzioni, ciascuna delle quali può essere pensata come un ordine che viene impartito alla macchina virtuale del linguaggio di programmazione utilizzato. Dal punto di vista sintattico, i costrutti di un linguaggio imperativo sono spesso identificati da verbi all'imperativo.
C'è una forte corrispondenza tra i concetti di programmazione imperativa e le componenti e le azioni del calcolatore di Von Neumann:

- variabili mutabili <-> celle di memoria
- dereferenziazione di variabili <-> istruzioni di caricamento
- assegnazione di variabili <-> istruzioni di archiviazione
- strutture di controllo <-> salti

### Programmazione orientata agli oggetti (OOP)
Paradigma di programmazione basato sul concetto di *oggetti*. Un oggetto è una struttura dati contenente informazioni sotto forma di campi (membri) e procedure (metodi). Ogni oggetto permette l'interazione con se stesso esponendo una interfaccia. I programmi sono scritti facendo interagire gli oggetti tra di loro.
Un linguaggio orientato agli oggetti è detto *puro* quando ogni valore è un oggetto (compresi valori primitivi e valori funzione). Se il linguaggio è basato su classi, questo si traduce nel fatto che ogni tipo è una classe.

### Programmazione funzionale (FP)
Paradigma di programmazione in cui il flusso di esecuzione del programma assume la forma di una serie di valutazioni di funzioni matematiche. In programmazione funzionale:

- non esistono valori mutabili, assegnazioni (per esperienza personale empirica: principalmente sostituiti da creazione di nuovi oggetti, passaggio di argomenti alle funzioni), cicli e strutture di controllo (per esperienza personale empirica: principalmente sostituiti da funzioni ricorsive, in particolare tail recursive. Spesso è possibile non scrivere esplicitamente le funzioni ricorsive che svolgono processi interativi ma basta sfruttare le librerie di funzioni ricorsive built-in) tipiche della programmazione imperativa. L'immutabilità dei dati dal punto di vista concettuale permette coerenza con le teorie matematiche, le quali non permettono mutazione (es: in matematica, non è possibile mutare cambiare un coefficiente ad un polinomio mantenendo il polinomio lo stesso. Tuttavia, questo è permesso in programmazione imperativa). Questo implica che lo stato del programma non viene variato assegnando nuovi valori ai dati, ma creando nuovi valori a partire dai precedenti.
- la programmazione si concentra sulle funzioni, ed in particolare su funzioni *pure*, coerentemente con quella che è la definizione matematica di funzione. Inoltre, le funzioni vengono trattate come *cittadini di prima classe*.

## Espressioni
Le entità di base sono le espressioni primitive. Tali espressioni primitive possono essere combinate a dare espressioni non primitive. Una espressione può essere un *identificativo* (es: x), una *selezione* (es: math.abs), un *literal* (es: "abc"), una *funzione anonima* (pensabile come un function literal), una applicazione di operatore, una applicazione di funzione, una *espressione for*, una *espressione condizionale*, un *blocco*.

### Valutazione
La valutazione di una espressione è il processo attraverso il quale essa viene ridotta ad un solo valore. Essa segue il *modello della sostituzione*. Tale modello è applicabile a tutte le espressioni purchè esse non presentino *effetti collaterali*. Infatti, nel caso in cui le espressioni non abbiano altro effetto a parte il valore da esse ritornato è possibile immaginare che la computazione proceda come la risoluzione di una espressione algebrica in cui ogni espressione è semplificata e sostituita con il valore ottenuto dalla sua valutazione, fino ad arrivare ad un unico valore irriducibile.
In Scala, i tipi dei valori ritornati dal processo di valutazione sono gli stessi di Java, ma a livello sintattico sono scritti con iniziali maiuscole: `Boolean` (`true`, `false`), `Char`, `Byte`, `Short`, `Int`, `Long`, `Float`, `Double`, `Unit` (analogo di *void*).


### Tipologie di espressioni

#### Identificativi - astrazione di espressioni
E' possibile riferirsi in modo astratto ad una espressione attribuendo ad essa un *identificativo*. Esistono due sintassi diverse per attribuire un identificativo ad una espressione. A seconda del tipo di assegnazione effettuata l'identificativo verrà valutato in modo differente.

- `val <id>[:<exprType>]=<expression>`. 
*Evaluation by value*. L'identificativo è valutato una e una sola volta, quando è incontrata la dichiarazione. Il valore ottenuto dalla valutazione dell'espressione a membro destro della sua dichiarazione è sostituito all'identificativo a membro sinistro della dichiarazione. Il valore così ottenuto è utilizzato ogni qual volta l'identificativo è richiesto successivamente alla sua dichiarazione nella valutazione del programma tramite modello della sostituzione.
- `def <id>[:<exprType>]=<expression>`. 
*Evalutation by name*. L'identificativo è valutato ogni volta che è richiesto successivamente alla sua dichiarazione nella valutazione del programma tramite modello della sostituzione. Se l'identificativo non è mai richiesto nel corso della semplificazione del programma, l'espressione esso associata in fase di dichiarazione non è mai valutata. Il valore esso associato è quello ottenuto sostituendo ad esso il valore ottenuto valutando l'espressione a membro destro della sua dichiarazione. NB: tale modello di valutazione è consistente in quanto in FP, non essendo permessi side effects, il valore ottenuto dalla valutazione dell'espressione associata all'identificativo produce sempre lo stesso risultato.
- `val <id>[:<exprType>]=<expression>`. 
*Lazy evaluation*. Se l'identificativo è richiesto in fase di semplificazione del programma tramite modello della sostituzione, allora esso è valutato e il valore ottenuto è utilizzato ogni qual volta l'identificativo è richiesto successivamente alla sua dichiarazione. Viceversa, se l'identificativo non è mai richiesto nel corso della semplificazione del programma, allora l'espressione esso associata in fase di dichiarazione non è mai valutata. Il valore esso associato è quello ottenuto sostituendo ad esso il valore ottenuto valutando l'espression a membro destro della sua dichiarazione. NB: tale modello di valutazione è consistente in quanto in FP, non essendo permessi side effects, il valore ottenuto dalla valutazione dell'espressione associata all'identificativo produce sempre lo stesso risultato. Un motivo per la quale essa non è sempre utilizzata è perchè anche le altre hanno vantaggi meno evidenti: l'evaluation by value permette alle volte di sostituire all'identificativo il suo valore già in fase di compilazione (mentre per lazy evaluation questo è effettuato a runtime); l'evaluation by name è necessaria in IP nel caso in cui il valore associato all'identificativo non è detto rimanga lo stesso tra due valutazioni successive (side effects, valori mutabile)

##### Regole
Un identificatore può essere:

- alfanumerico: cominciante con una lettera, e seguito da una serie di lettere o numeri.
- simbolico: cominciante con un operatore simbolico, e seguito da altri operatori simbolici
- misto: cominciante con una parte alfanumerica e terminante con una parte simbolica, separate da `_`

#### Selezioni
Una selezione permette di valutare una proprietà di un valore tramite suo identificativo. In Scala, la selezione è effettuata in *dot notation*. La valutazione di una selezione <idVal>.<idProp> è effettuata tramite i seguenti passaggi:

- valutazioe dell'espressione idVal
- valutazione dell'espressione idProp, in cui è sostituito il alla parola chiave `this` il valore ottenuto dalla valutazione dell'espressione idVal

#### Operatori - composizione di espressioni
E' convenzione far terminare con `:` gl operatori con proprietà associativa da destra verso sinistra (mentre di default associano da sinistra verso destra)

##### operatori logici

- `!`: not
- `&&`: and (corto-circuito. E' più corretto pensare la tabella della verità di and come la seguente: true && e -> e; false && e -> false)
- `||`: or (corto-circuito. E' più corretto pensare la tabella della verità di or come la seguente: true || e -> tuue; false && e -> e)

##### operatori di comparazione

- `<`: minore
- `<=`: minore o uguale
- `==`: uguale
- `!=`: diverso
- `>`: maggiore
- `>=`: maggiore o uguale

##### valutazione di operatori applicati
La valutazione dell'applicazione di un operatore segue il seguente processo, fino a che non risulta in un valore:

1. identificazione dell'operatore più a sinistra, una volta soddisfatte le regole di precedenza. La precedenza di un operatore è stabilita dal suo primo carattere. In ordine crescente di precedenza, si hanno: tutte le lettere; `|`; `^`; `&`; `<`, `>`; `=`, `!`; `:`; `+`, `-`; `*`, `/`, `%`; tutti gli altri caratteri speciali.
2. valutazione degli operandi dell'operatore da sinistra verso destra
3. applicazione dell'operatore agli operandi

#### Espressioni condizionali

##### if

Segue la sintassi:
`if (<predicate>) <ifExpression>`
`else <elseExpression>`
predicate è una espressione che ritorna un valore Boolean, mentre ifexpression e elseExpression sono delle espressioni. Se il predicato è true, allora viene valutata l'espressione ifExpression; se il predicato è false, allora viene valutata elseExpression.
La differenza rispetto ad un linguaggio imperativo sta nel fatto che a seguito della valutazione del predicato è valutata una espressione, e non svolto un blocco di istruzioni.

##### pattern matching
E' pensabile come un modo per definire una funzione a tratti. La sua siontassi è una generalizzazione della proposizione switch. La sintassi per effettuare pattern matching è:
`<expressionToMatch> match {`
`    case <pattern1> => <expression1>`
`    [case <pattern2> => <expression2>]`
`    [...]`
`}`
expressionToMatch è l'espressione della quale si vuole cercare una corripondenza. L'utilizzo di tuple in pattern matching permette di utilizzare una serie di expressionToMatch al posto di una singola expressionToMatch  e una serie di pattern per ciascun case; in tal caso si ha corrispondenza se è trovata corrispondenza di ciascuna expressionToMatch con il relativo pattern (in ordine posizionale). Un pattern può essere un costruttore, una variabile, una wildcard (*_*), una costante, o una combinazione di queste ultime. L'espressione di pattern matching viene valutata nel seguente modo: una corrispondenza tra expressionToMatch e uno dei pattern è cercata, nell'ordine in cui i pattern compaiono. Una corrispondenza è trovata se:

- costruttore. Un pattern costruttore `C(p1, ..., pn)` ha corrispondenza con tutti i valori dei tipi A < C che sono stati costruiti con argomenti che hanno corrispondenza col pattern p1,...,pn
- tupla. Un pattern tuple `(p1, ..., pn)` è equivalente al pattern costruttore `scala.Tuplen(p1, ..., pn)` (tuplen è una clase class delle tuple a n elementi).
- variabile. Un pattern varaibile `x` ha corrispondenza con un qualsiasi valore, e vincola il nome di tale variabile al valore con cui ha corrispondenza
- costante. Un pattern costante `c` ha corrispondenza con un qualsiasi valore pari a c (nel senso di ==).

Quando la prima corrispondenza è trovata, expressionToMatch è sostituita con l'espressione expressionN in cui i riferimenti delle eventuali variabili trovate in patternN sono sostituiti all'interno di expressionN. Se più pattern presentano corrispondenza con expressionToMatch, solo il primo è considerato. Se nessun pattern presenta corrispondenza con expressionToMatch, allora una eccezione `MatchError` è gettata.
La differenza rispetto ad un linguaggio imperativo sta nel fatto che a seguito di una corrispondenza è valutata una espressione, e non svolto un blocco di istruzioni.

###### case class
Una case class è una classe regolare la quale esporta i parametri del suo costruttore e fornisce una decomposizione oggetto->argomenti ricorsiva tramite meccanismo di pattern matching.
Una case class è dichiarata tramite una normale dichiarazione di classe cui è preposta la parola chiave `case`.
Una nuova istanza di una case class può essere creata invocando il relativo costruttore anche senza la parola chiave new.
L'utilizzo di pattern matching + case class permette di utilizzare il metodo di decomposizione che precede accentramento dei metodi delle sottoclassi in un unico metodo della superclasse che effettui controllo di tipo sugli argomenti + accesso ai valori delle proprietà. Se lo scopo della case class è solo wrappare gli argomenti e permetterne esportazione tramite pattern matching è anche possibile non implementare alcun corpo per la classe.

#### Espressione for
Una *espressione for* è una notazione che permette di effettuare iterator comprehension.
L'utilizzo di tale notazione permette di sostituire la cascata di una serie di operazioni di filtro e mappa su un iteratore. Il suo utilizzo è da intendersi per quei casi in cui il livello di astrazione introdotto dall'utilizzo delle HOF per gli stessi fini renda il programma meno intelleggibile. In generale, il loro utilizzo permette tra le altre cose di:

- esprimere in modo conciso un'operazione di costruzione di una collezione di cui ciascun valore è ottenuto valutando una espressione per ogni valore dei generatori eventualmente filtrati. 
- effettuare destructuring di un iteratore, o più comunemente di iteratori annidati
- effettuare delle query su dei database (una query non è altro che una operazione di filter, eventualmente seguita da map).

La sintassi di una notazione for è la seguente:
`for (<generator><filter>[<generator><filter>][...]) yield <expression>`
dove

- un generatore è espresso tramite la sintassi `<pattern> <- <generatorExpression>`. pattern è un pattern di cui è effettuato matching con la generatorExpression. generatorExpression è una espressione il cui valore è un iteratore (compreso quello di funzione applicata con risultato un operatore). Le regole utilizzate per effettuare match del pattern sono quelle del pattern matching. Un generatore può essere espresso come una serie di generatori; se più generatori in serie sono incontrati essi si comportano come un unico generatore in cui ciascun generatore è fatto variare su tutti gli elementi associati al valore iterabile della sua generatorExpression per ciascun valore associato alla serie generatorExpression dei generatori che lo precedono (operazione di flatMap).
- un filtro è espresso tramite la sintassi `if <predicate>`. Predicate è una espressione con valore booleano, tipicamente parametrizzata usando come identificativi dei parametri i pattern del generatore. Il filtro definisce quali valori del generatore posto a valle sono considerati (operazione di filter). E' possibile porre in cascata più coppie generatore-filtro; in tal caso, i generatori delle coppie generatore-filtro più a valle sono associati all'iteratore ottenuto dalle coppie generatore-filtro più a monte.
- expression è l'espressione che viene valutata per tutti quegli elementi risultanti dal generatore che superano i filtri. Tipicamente tale espressione è parametrizzata usando come identificativi dei parametri i pattern del generatore (operazione di map).

La differenza rispetto ad un linguaggio imperativo sta nel fatto che a seguito di una espressione for è ritornata una collezione, e non effettuata una serie di operazioni.

##### Traduzione e compatibilità
Una espressione for è tradotta in operazioni di flatMap, filter e map. In particolare:

- un generatore ottenuto dalla serie di più generatori elementari è ottenuto concatenando coppie di generatori elementari successivi tramite operazioni di flatMap.
- un filtro ottenuto dalla serie di più filtri è ottenuto come una operazione di filterWith (filter implementato in maniera lazy) per ogni filtro elementare.
- l'operazione di yield di una espressione è tradotta in una operazione di map

Una espressione for è compatibile con generatori di qualsiasi tipo iterabile. In particolare, una espressione for è compatibile con generatori di un qualsiasi tipo che supporti operazioni di flatMap, filterWith e map. E' quindi possibile utilizzare espressioni di for anche con generatori di tipi user defined che implementino dei metodi con identificativi flatMap, filterWith e map.


#### Blocchi
Un blocco è una espressione composta da una serie di dichiarazioni o espressioni raggruppate. Tipicamente esso consiste di una serie di dichiarazioni ausiliarie terminanti con una espressione che in fase di valutazione stabilisce il valore del blocco stesso. In Scala, la sintassi per scrivere un blocco prevede che:

- le dichiarazioni e le espressioni siano racchiuse tra `{}`, secondo la scrittura
- ciascuna dichiarazione ed espressione sia posta su una riga differente. Se più dichiarazioni o espressioni sono riportare sulla stessa riga, allora esse devono essere separate da `;`. Se una dichiarazione o espressione occupa più righe, allora essa deve essere racchiusa tra `()` deve andare a capo terminando la riga con un operatore.

##### Ambiti
L'*ambito (scope)* di una associazione espressione - identificativo è la porzione di codice in cui tale associazione è valida. Le regole principali con sui sono stabiliti gli ambiti in Scala sono le seguenti:

* l'unico costrutto che crea un ambito locale sono i blocchi
* ogni ambito locale ha visibilità su tutti gli ambiti locali che lo contengono

In particolare, la seconda regola fa sì che si parli per Scala di *ambito lessicale (lexical scope)*. Si dice ambito lessicale l'approccio secondo il quale ogni identificativo ha visibilità internamente al blocco o funzione (funzione nel caso di Scala) in cui è dichiarata. E' detto ambito lessicale in quanto è possibile determinare lo stesso semplicemente leggendo il codice.  
L'ambito lessicale permette di dichiarare identificativi privati all'interno di porzioni di codice (tipicamente all'inteno di funzioni). Questo permette di:

- evitare il sovrapopolamento dei namespace (le dichiarazioni effettuate all'interno non hanno visibilità nei blocchi più esterni)
- evitare di dover effettuare dichiarazioni multiple (le dichiarazioni effettuate in blocchi esterni contenenti il blocco in questione hanno visibilità all'interno di quest'ultimo)

#### funzioni anonime
Una *funzione anonima* è la rappresentazione di una funzione all'interno del codice di un programma. Tale notazione è la più semplice per ottenere una funzione all'interno del programma. Esistono due possibili sintassi per funzioni anonime:
- `(<parSerie>) => <expression>`
- `<expression>`. In questa sintassi abbreviata i parametri sono espressi tramite le wildcard `_`. Il compilatore assume implicitamente che ogni nuova wildcard incontrata da sinistra verso destra rappresenti un parametro diverso. Per tale ragione, questa sintassi è utilizzabile solo quando ogni parametro compare una sola volta all'interno dell'espressione.
Nel caso in cui il tipo dei parametri sia inferibile dal contesto, la sintassi è ulteriormente abbreviabile omettendo i tipi dei paramentri.
Utilizzi tipici delle function literal è nel passaggio parametri di tipo funzione o nel ritornare valori di tipo funzione, nel caso in cui non sia necessario definire la funzione in questione (utilizzo puntuale) e desiderabile l'utilizzo di una sintassi più concisa.

#### funzioni applicate
Una funzione applicata, in quanto ritornante un valore, è una espressione. La valutazione dell'applicazione di una funzione segue il seguente processo:

- Valutazione degli argomenti: valutazione di tutti gli argomenti della funzione (operandi dell'operatore), da sinitra a destra
- Valutazione della funzione. Consiste contemporaneamente delle seguenti valutazioni:
  - valutazione dell'identificativo della funzione: sostituzione dell'applicazione della funzione da parte del corpo della funzione
  - valutazione del corpo di funzione: sostituzione dei parametri formali da parte degli effettivi argomenti

In base a quale dei due passaggi è eseguito prima, si distinguono due processi di valutazione.
- *call by value*. E' eseguita prima la valutazione degli argomenti, poi la valutazione della funzione. Ha il vantaggio di valutare gli argomenit una sola volta. E' il processo utilizzato da Scala
-  *call by name*. E' eseguita prima la valutazione della funzione, poi la valutazione degli argomenti. Ha il vantaggio di non valutare gli argomenti non utilizzati nella valutazione del corpo della funzione.

Se le funzioni sono pure e sia la valutazione di argomenti che della funzione hanno un termine, allora tali processi portano allo stesso risultato. Più in generale, tuttavia, vale sole l'implicazione: processo dii valutazione call by value termina => processo di valutazione call by name termina (quindi call by name termina più spesso)

## Funzioni
Una funzione A => B è una relazione che lega ciascun elemento *a* di *A* ad uno e un solo elemento *b* di *B*, tale che *B* è determinato solo dal valore di *a*. Questo imnplica, tra le altre cose, che una

### Concetti
Nei linguaggi funzionali, quali Scala, le funzioni sono trattate come cittadini di prima classe e sono pure.

#### Cittadini di prima classe
Un *cittadino di prima classe* è un valore che supporta tutte le operazioni possibili per gli altri valori. Tali operazioni tipicamente sono:

- dichiarazione in qualunque punto del programma
- passaggio come parametri alle funzioni
- ritorno come risultati dalle funzioni
- possibilità di essere composte tramite opportuni operatori

#### Funzioni pure
Una funzione è detta *funzione pura* se non ha *effetti collaterali* (*side effects*). Il fatto che una funzione sia pura, è in realtà implicito nella definizione di funzione stessa. Tuttavia, definire una funzione pura è spesso effettuato per enfatizzare il concetto e distinguerla da una *procedura* (porzione di codice parametrizzata che può avere effetti collaterali), spesso impropriamente chiamate funzioni.
Una funzione è detta avere effetti collaterali se ha effetti osservabili sull'esecuzione del programma a parte il ritornare un valore. Ad esempio, una funzione non è pura se:

- modifica (inizializzazione compresa) una variabile o una struttura di dati in place
- getta una eccezione o interrompe il flusso di esecuzione con un errore
- legge o scrive da/su risorse esterne

L'utilizzo di funzioni pure ha vantaggi in termini di:

- modularità. Rendono il programma veramente componibile, in quanto ciascuna di esse è indipendente dal resto del programma stesso.
- generalizzazione. Sono meno vincolate ad aspetti specifici del programma stesso, e quindi anche più riutilizzabili.
- comprensibilità. Sono 'scatole nere': si può badare solo a quello che viene fornito loro in input e a quello che viene ritornato in output, senza preoccuparsi di come tale processo avvenga.
- facilità di test. Permettono un ragionamento locale, siccome non vanno a influenzare il resto del programma se non per il valore ritornato.

Una definizione formale di funzione pura può essere data introducento il concetto di *trasparenza referenziale* (*referential trasparency*):

- *referential trasparency*: una espressione *e* è detta trasparente referenzialmente se, per un qualsiasi programma *p*, tutte le sue occorrenze possono essere sostituite dal risultato della valutazione di *e* senza che il significato di *p* venga influenzato.
- *funzione pura*: una funzione *f* è detta pura se l'espressione *f(x)* è trasparente referenzialmente per tutti i valori di x.

### dichiarazione
Segue la sintassi:
`def <id>'['<typeParSerie>'[*]]'(<valParSerie>) [: <returnType>]= <expression>`
Essa è composta, nell'ordine, da:

- identificativo utilizzato per la funzione, preceduto dala parola chiave `def` che introduce un assegnamento *by name*
- serie dei parametri di tipo che la funzione riceve, posta tra parentesi quadre. Se più parametri sono presenti, essi devono essere separati da `,`.
- serie dei parametri di valore che la funzione riceve, posta tra parentesi tonde. Essendo Scala un linguaggio fortemente tipizzato, esso si aspetta che venga dichiarato il tipo di ogni variabile. La dichiarazione di un parametro segue quindi la sintassi: `<parId1>: [=>] <parType1>`. L'operatore `=>` è utilizzato in caso si voglia effettuare valutazione del relativo argomento by name anzi che by value (default) al momento della valutazione della funzione. L'operatore `*` è utilizzato nel caso si voglia indicare un *parametro ripetuto* un numero indefinito di volte. Se più parametri sono presenti, essi devono essere separati da `,`.
- tipo del valore ritornato (obbligatorio solo per funzioni ricorsive), preceduto da `:`
- espressione associata alla funzione, preceduto dall' operatore di assegnamento `=`

### chiamata
La sintassi per la chiamata di una funzione è:
`<functionId>'['<typeArgsSerie>']'(<valArgsSerie>)`

### composizione

#### Funzioni annidate
##### dichiarazioni annidate
In un linguaggio funzionale le funzioni sonocittadini di prima classe, e in quanto tali possono essere dichiarate in qualsiasi punto del programma. In particolare, esse possono essere dichiarate all'interno di altre funzioni, realizzando così funzioni annidate. Questo permette ad esempio di sfruttare i vantaggi permessi dall'ambito lessicale quali:

- evitare il sovrapopolamento dei namespace (le dichiarazioni effettuate all'interno di un blocco non hanno visibilità nei blocchi più esterni)
- evitare di dover effettuare dichiarazioni multiple (le dichiarazioni effettuate in blocchi esterni contenenti il blocco in questione hanno visibilità all'interno di quest'ultimo). In particolare, questo fa sì che non sia necessario passare come argomenti alle funzioni più interne gli argomenti già passati alle funzioni più esterne (sono già nel loro namespace).

##### chiamata di altre funzioni
Una funzione nel suo corpo puà chiamare altre funzioni, a vantaggio della modularità. In particolare una funzione potrà chiamare anche funzioni definite internamente (annidate) e se stessa. Si definiscono:

- *Tail call*: chiamata a una sottofunzione effettuata come ultima azione di una funzione. Una tail call può essere implementata a livello del calcolatore senza aggiungere al *call stack* un ulteriore *stack frame* rispetto a quello della funzione chiamante.
- *Funzione ricorsiva*: funzione la quale effettua una chiamata a se stessa all'interno del suo corpo. Tipicamente, una funzione ricorsiva è strutturata in modo che, dato un set di elementi su cui effettuare una operazione atomica, effettui l'operazione su un elemento dell'insieme per poi chiamarsi sul resto dell'insieme (es: per funzione ricorsiva su una lista, effettua operazione sulla testa della lista e chiama se stessa sulla coda della lista. Il fatto che la funzione si chiami su insiemi sempre più piccoli è fondamentale perchè si abbia convergenza). Perchè la ricorsione abbia fine, esse devono prevere almeno una espressione condizionale in cui uno o più casi non prevedano ricorsione, ponendo fine alla ricorsione stessa (es: lista vuota).
- *Tail recursion*: chiamata di una funzione a se stessa effettuata come ultima azione della funzione stessa. E' un caso particolare di tail call.
- *Funzione tail recursive*: funzione la quale effettua una tail call a se stessa all'interno del suo corpo. Tipicamente, perchè una funzione ricorsiva sia tail recursive, viene passato ad essa un parametro nel quale viene salvato il risultato parziale ottenuto dalle chiamate precedenti.

Le funzioni ricorsive implementano processi iterativi. In particolare, le funzioni tail recursive implementano processi analoghi ai cicli. Spesso è possibile non scrivere esplicitamente le funzioni ricorsive che svolgono processi interativi ma basta sfruttare le librerie di funzioni ricorsive built-in
In Scala, sono ottimizzate solo le chiamate dirette a funzioni tail recursive. Per richiedere esplicitamente che una funzione implementata in modo tail recursive venga eseguita come tale è possibile far precedere la sua dichiarazione dall'annotazione `@tailrec` (annotazione `scala.annotation.tailrec`, da importare).

#### Funzioni di ordine superiore
In un linguaggio funzionale le funzioni sonocittadini di prima classe, e in quanto tali possono tra le altre cose essere passate come argomenti e ritornate a/da altre funzioni. Funzioni che ricevono altre funzioni come argomento o che ritornano altre funzioni sono dette *funzioni di ordine superiore* (HOF).

##### passaggio
Ricevere funzioni come argomento permette di astrarre sui compiti svolti da parte della funzione ricevente. Tra gli altri vantaggi, questo permette una maggior modularità.

##### ritorno
Ritornare funzioni permette di realizzare funzioni che creano altre funzioni. Tra gli altri vantaggi, questo permette di poter passare da funzioni che prendono una lunga serie di paramentri e ritornano un risultato puntuale, a funzioni più astratte che, presa una parte dei parametri, ritornino funzioni come risultati intermedi. Queste ultime, prese la restante parte dei parametri, realizzeranno compiti concreti o a loro volta prenderanno solo una parte dei restanti paramentri e restituiranno altre funzioni meno astratte (Es: anzi che realizzare una funzione che restituisce la somma degli interi in un intervallo argomento mappati secondo una certa trasformazione argomento, è possibile creare una funzione più generale che, ricevuta la funzione trasformazione, restituisce una funzione che effettui la somma degli interi in un intervallo argomento). La trasformazione della valutazione di una funzione che prende una serie di parametri nella valutazione di una serie di funzioni che prendono un paramentro in matematica ed informatica è della *currying*.
Il currying permette di non scrivere funzioni con lunghe liste di paramentri: i parametri presi dalle funzioni ritornate non devono essere passati alla funzione ritornante. E' quindi buona norma spezzare le funzioni tramite currying quando ragionevole (e spesso e volentieri lo è quando la funzione più generale riceve come paramentri altre funzioni).
In Scala, funzioni che ritornano altre funzioni sono così importanti da essere dichiarate con una loro sintassi:
`def <id>(<parSerie1>)(<parSerie2>)[(...)] [: <returnType>]= <expression>`
Tale notazione è analoga a quella utilizzata per la dichiarazione di una funzione standard, con la differenza che al posto che indicare un singolo passaggio di paramentri, sono indicati tra tonde più passaggi di parametri. Nello specifico, sono indicati da sinistra verso destra i parametri ricevuti dalla funzione più esterna e a seguire da ciascuna funzione intermedia ritornata.


## Collezioni (sottoclasse degli iterabili)

### Proprietà comuni

#### Accesso

- ottenere una collezione contenente i primi n argomento elementi della collezione corrente: `this take <n>`. Se n è maggiore del numero di elementi contenuti nella colleziomne, allora la collezione corrente è ritornata. Supp: usando questo metodo su una generica collezione che non sia una sequenza, i valori contenuti saranno processati nell'ordine in cui sono incontrati nella collezione che non è definto a priori.
- ottenere una collezione contenente gli elementi della corrente ad eccezione dei primi n: `this drop <n>`.  Supp: usando questo metodo su una generica collezione che non sia una sequenza, i valori contenuti saranno processati nell'ordine in cui sono incontrati nella collezione che non è definto a priori.

#### Test

- ottenere true se esiste un elemento della collezione che soddisfa la proprietà p argomento, false viceversa: `this exists <p>`. p è una funzione che prende un elemento della collezione e ritorna un booleano
- ottenere true se tutti gli elementi della collezione soddisfano la proprietà p argomento, false viceversa: `this forall <p>`. p è una funzione che prende un elemento della collezione e ritorna un booleano
- ottenere l'indice del primo elemento della collezione pari a x argomento, o -1 se x non appare nella sequenza: `this indexOf <x>`. Supp: usando questo metodo su una generica collezione che non sia una sequenza, i valori contenuti saranno processati nell'ordine in cui sono incontrati nella collezione che non è definto a priori. Ha più senso sulle sequenze.
- ottenere true se la collezione corrente contiene un elemento argomento, false viceversa (l'interrogazione per le mappe è effettuata sulle chiavi): `this contains <x>`
- ottenere true se la collezione è vuota, false se la collezione è piena: `this.isEmpty`

#### HOF: mappe

- ottenere una collezione ottenuta applicando una funzione f argomento a tutti gli elementi della lista: `this map <f>`. La funzione f deve essere una funzione ad un argomento (rappresentante un elemento della collezione).
- ottenere una sequenza applicando una trasformazione f a tutti gli elementi della sequenza corrente e concatenando i risultati: `this flatMap <f>`. f è una funzione che prende un elemento della collezione corrente e ha come risultato una collezione dello stesso tipo. La colezione è attraversata una sola volta.

#### HOF: filtri

- ottenere una collezione composta da quegli elementi della collezione che soddisfano un test espresso da una funzione f argomento: `this filter <f>`. La funzione f deve essere una funzione ad un argomento (rappresentante un elemento della collezione) con valore ritornato booleano.
- ottenere una collezione composta da quegli elementi della collezione che non soddisfano un test espresso da una funzione f argomento: `this filterNot <f>`. La funzione f deve essere una funzione ad un argomento (rappresentante un elemento della collezione) con valore ritornato booleano.
- ottenere una tupla a due elementi, in cui il primo elemento è una collezione ottenuta come da applicazione di filter alla collezione mentre il secondo elemento è una collezione ottenuta come da applicazione di filterNot alla collezione: `this partition <f>` (utilizzando tale metodo la collezione è però attraversata una sola volta)
- ottenere una collezione composta dai primi elementi che soddisfano un test espresso da una funzione f argomento (fermandosi al primo argomento che non soddisfa il test): `this takeWhile <f>`. La funzione f deve essere una funzione ad un argomento (rappresentante un elemento della collezione) con valore ritornato booleano. Supp: usando questo metodo su una generica collezione che non sia una sequenza, i valori contenuti saranno processati nell'ordine in cui sono incontrati nella collezione che non è definto a priori. Ha più senso sulle sequenze.
- ottenere una collezione ottenuta scartando dalla collezione i primi elementi che soddisfano un test espresso da una funzione f argomento (e cominciando a tenere gli elementi a partire dal primo argomento che non soddisfa il test): `this dropWhile <f>`. La funzione f deve essere una funzione ad un argomento (rappresentante un elemento della collezione) con valore ritornato booleano. Supp: usando questo metodo su una generica collezione che non sia una sequenza, i valori contenuti saranno processati nell'ordine in cui sono incontrati nella collezione che non è definto a priori.
- ottenere una tupla a due elementi, in cui il primo elemento è una collezione ottenuta come da applicazione di takeWhile alla collezionea mentre il secondo elemento è una collezione ottenuta come da applicazione di dropWhile alla collezione: `this span <f>` (utilizzando tale metodo la collezione è però attraversata una sola volta). Supp: usando questo metodo su una generica collezione che non sia una sequenza, i valori contenuti saranno processati nell'ordine in cui sono incontrati nella collezione che non è definto a priori. Ha più senso sulle sequenze.

#### HOF: riduzioni

- ottenere un unico valore a partire dagli elementi della collezione ridotti cominciando dalla testa tramite una funzione di riduzione argomento e a partire da un valore accumulatore argomento: `this foldLeft <s> (<f>)`. foldLeft è definita tramite currying. f deve essere una funzione a due argomenti(rappresentanti i due argomenti combinati) con primo argomento del tipo dell'accumulatore e secondo argomento dello stesso tipo dei valori della collezione (potenzialmente differenti) e valore risultante dello stesso tipo dell'accumulatore. Il valore risultante è ottenuto combinando gli elementi della collezione nel seguente modo: f(f(f(s,0),1)...,n), dove con n si è indicato l'n-esimo elemento della collezione e con s l'elemento di partenza per la riduzione, detto *accumulatore*. Il valore di partenza s è anche il valore risultante nel caso base (collezione vuota), e tipicamente coincide con l'elemento neutro dell'operatore f. Supp: usando questo metodo su una generica collezione che non sia una sequenza, i valori contenuti saranno processati nell'ordine in cui sono incontrati nella collezione che non è definto a priori. Tuttavia, left si riferirà comunque alla posizione dell'argomento associato all'accumulatore.
- ottenere un unico valore a partire dagli elementi della collezione ridotti cominciando dalla testa tramite una funzione di riduzione argomento: `this reduceLeft <f>`. A differenza di foldLeft, non è possibile passare esplicitamente il valore dell'accumulatore che è assunto pari all'elemento in testa alla collezione. Di conseguenza, reduceLeft non accetta collezione nulla e ritorna il valore f(f(f(0,1),2)...,n). E' possibile ottenere lo stesso comportamento di foldLeft inserendo il valore iniziale desiderato per l'accumulatore come elemento in testa alla collezione. Supp: usando questo metodo su una generica collezione che non sia una sequenza, i valori contenuti saranno processati nell'ordine in cui sono incontrati nella collezione che non è definto a priori. 
- ottenere un unico valore a partire dagli elementi della collezione ridotti da destra tramite una funzione di riduzione argomento e a partire da un valore accumulatore argomento: `this foldRight <s> (<f>)`. Come foldLeft ma con riduzione da destra. Questo significa che il valore risultante sarà f(0,f(1,...f(n-1,f(n,s))), il che implica anche che f prende come primo argomento un valore del tipo degli elementi della collezione, come secondo argomento un valore del tipo del'accumulatore e ha valore risultante dello stesso tipo dell'accumulatore. Supp: usando questo metodo su una generica collezione che non sia una sequenza, i valori contenuti saranno processati nell'ordine in cui sono incontrati nella collezione che non è definto a priori. Tuttavia, right si riferirà comunque alla posizione dell'argomento associato all'accumulatore.
- ottenere un unico valore a partire dagli elementi della collezione ridotti da destra tramite una funzione di riduzione argomento: `this reduceRight <f>`. Come reduceLeft ma con riduzione da destra. Questo significa che il valore risultante sarà f(0,f(1,...f(n-2,f(n-1,n))). Supp: usando questo metodo su una generica collezione che non sia una sequenza, i valori contenuti saranno processati nell'ordine in cui sono incontrati nella collezione che non è definto a priori.

#### Conversioni

- ottenere un iterabile dalla collezione corrente: `this.toIterable`
- ottenere una sequenza dalla collezione corrente: `this.toSeq`
- ottenere una lista dalla collezione corrente: `this.toList`
- ottenere uno stream dalla collezione corrente: `this.toStream`
- ottenere un array dalla collezione corrente: `this.toArray`
- ottenere una stringa dal valore corrente: `this.toString`
- ottenere un set dalla collezione corrente: `this.toSet`
- ottenere una mappa dalla collezione di coppie corrente: `this.toMap`


### Sequenze
Sono collezioni iterabili di dati ordinati.

- ottenere una sequenza dalla collezione corrente: `this.toSeq`

#### proprietà comuni
Alcune proprietà comuni a tutti i valori sequenza sono:

- ottenere una sequenza pari alla sequenza corrente cui è inserito un elemento all'inizio (nel senso dell'ordinamento): `<x> +: this` (+ dalla parte dell'elemento inserito)
- ottenere una sequenza pari alla sequenza corrente cui è inserito un elemento in fondo (nel senso dell'ordinamento): `this :+ <x>` (+ dalla parte dell'elemento inserito)
- ottenere una sequenza contenente gli stessi elementi della corrente, eccetto all'indice n argomento in cui conterrà l'elemento x argomento: `this updated (<n>, <x>)`
- ottenere una sequenza a livello di annidamento 0 (nessuna sequenza annidata) dalla sequenza corrente potenzialmente annidata: `this.flatten`
- ottenere una serie di coppie (tuple a due elementi) ottenute affiancando gli elementi in posizione corrispondente della sequenza corrente e di una sequenza argomento: `this zip <xs>`
- ottenere una coppia (tupla a due elementi) in cui la prima sequenza e la seconda sequenza sono ottenute rispettivamente dai primi e dai secondi elementi delle coppie di una sequenza di coppie argomento: `this.unzip`. E' l'operazione inversa di zip.
- ottenere una sequenza invertendo la sequenza corrente: `this.reverse`
- ottenere una mappa in cui le chiavi sono date dal set di valori ottenuti applicando una funzione f argomento a tutti i valori della sequenza, e il valore associato alla chiave k è una sequenza contenente i valori x della sequenza iniziale per i quali f(x)=k: `this groupBy <f>`
- ottenere una sequenza contenente solo gli elementi distinti della sequenza corrente: `this.distinct`
- ottenere la somma degli elementi di una sequenza di valori numerici: `this.sum`
- ottenere il prodotto degli elementi di una sequenza di valori numerici: `this.product`
- ottenere il massimo di tutti gli elementi di una sequenza (un `Ordering` deve esistere): `this.max`
- ottenere il minimo di tutti gli elementi di una sequenza (un `Ordering` deve esisitere): `this.min`

#### liste
Sono sequenze di dati omogenei.

- ottenere una lista contenente una serie di oggetti argomento: `List(<objsSerie>)` (costruttore `List`)
- ottenere una lista dalla collezione corrente: `this.toList`

##### Implementazione
Sono implementate tramite oggetti di due classi, entrambe con il tratto `List[T]`:

- `Nil`: oggetto rappresentante una lista vuota. L'oggetto Nil relativo al tipo List[T] è ottenuto tramite la sintassi: `List.empty[T]` (anche la lista vuota ha associato un tipo)
- `Cons`: oggetto rappresentante una cella. Ha due membri:
  - `head`. Contiene l'oggetto associato alla cella corrente.
  - `tail`. Contiene il riferimento al successivo elemento della lista.

Vista la loro struttura implementativa lineares, l'accesso in testa ha complessità O(1), mentre l'accesso all'ultimo elemento ha complessità O(n), con n numero di celle della lista.

##### pattern matching
Tipicamente le operazioni sulle liste prevedono un controllo su se la lista è piena o vuota (Nil) e, nel caso la lista sia piena, delle operazioni con head e tail della lista. Un modo comodo di realizzare tali operazioni è con pattern matching in cui si utilizzano come pattern `Nil` per lista vuota e `x::xs` per lista piena (il che permette anche accesso diretto a head e tail) o due pattern matching annidati per indicare liste annidate.


##### proprietà

###### base

- ottenere il numero degli elementi di una lista: `this.length`

###### slice

- ottenere il primo elemento della lista (eccezione se lista corrente vuota): `this.head`.
- ottenere il una lista data da tutti gli elementi della corrente eccetto il primo (eccezione se lista corrente vuota): `this.tail`.
- ottenere l'ultimo elemento della lista (eccezione se lista corrente vuota): `this.last`
- ottenere un lista data da tutti gli elementi della corrente tranne l'ultimo (eccezione se lista corrente vuota): `this.init`
- ottenere l'n-esimo argomento elemento della lista: `this(<n>)` (zucchero sintattico per `this apply <n>`)

###### creazione

- creare una lista con head argomento e tail argomento: `<head> :: <tail>` (con associatività a destra) o `<tail> .:: <head>` (con associatività a sinistra, come di default)
- creare una lista dalla concatenazione di una lista xs seguita da una lista ys argomento: `<xs> ++ <ys>` o `<xs> ::: <ys>` (con associatività a destra)
- creare una lista dalla concatenazione di una lista ys seguita da una lista xs argomento: `<xs> .::: <ys>` (con associatività a sinistra)

###### ricerca

- ordinare una lista con una funzione di ordinamento argomento: `this sort <f>`. f è una funzione che prende due valori rappresentanti due elementi della lista, e ha risultato true se e solo se il primo è minore del secondo, false viceversa.c


#### vettori
Sono sequenze di dati omogenei.

- ottenere un vettore contenente una serie di oggetti argomento: `Vector(<objsSerie>)` (costruttore `Vector`)

##### Implementazione

###### Struttura
A differenza delle liste, i vettori sono implementati come degli alberi tendenzialmente poco profondi. L'albero rappresentante un vettore è una struttura in cui ciascun nodo è un blocco base composto a sua volta da celle.
Ogni blocco base è pensabile come una lista di 32=2^5 celle in cui ciascuna cella, a differenza delle celle di una lista, oltre che il puntatore al blocco base successivo ha anche un puntatore alla testa di un blocco base posto nel livello subito inferiore dell'albero. Ne consegue che un vettore a L livelli contiene a 2^(5*L) celle. La complessità di accesso ad un elemento è data circa dal numero di livelli dell'albero, e quindi è log_32(n), con n numero di elementi del vettore.

###### Vettori vs liste
A fronte della struttura implementativa, un vettore presenta vantaggi rispetto ad una lista se:

- si intende effettuare numerose operazioni 'bulk', come map, filter e reduce. Infatti, i tempi di accesso agli elementi intermedi è più bilanciato. Inoltre, il tempo totale necessario ad accedere a tutti gli elementi della struttura è inferiore.

Al contrario, un vettore risulta svantaggioso rispetto ad una lista se:

- si intende effettuare numerose operazioni in testa o tendenzialmente vicino alla testa (con testa intesa nel senso dell'ordinamento. Nella lista l'accesso in testa è O(1))
- si desidera effettuare operazioni di accesso al primo elemento e all'ultimo elemento (nel senso dell'ordinamento) in un tempo costante.


###### Dinamica di inserzione di un valore

La dinamica inserzione di un valore in un vettore, essendo le strutture dati in FP immutabili, consiste nella creazione di un nuovo vettore simile al precendente ma contenente in più l'elemento in questione. La procedura seguita, valida più in generale per una qualsiasi struttura ad albero, è la seguente.
Detti:

- *e* l'elemento da 'inserire'
- *T* l'albero esisitente
- *B* il blocco sull'ultimo livello di T che contiene l'ultimo valore (nel senso dell'ordinamento)
- *B^n* l'n-esimo blocco parente del blocco *B*
- *L* il livello di *B* nell'albero *T*

Allora:

1. è individuato *B*
2. è creato un nuovo blocco *b*. Tale blocco *b*:
  - concettualmente si posiziona ad un livello *l*=*L* se il livello *L* non è saturo, o ad un livello *l*=*L*+1 se il livello *L* è saturo.
  - contiene solo l'elemento *e* se le celle del blocco *B* sono tutte piene, o gli elementi contenuti in *B* con l'aggiunta di *e* se le celle del blocco *B* non sono tutte piene.
3. è creato un blocco *b'* simile al blocco parente di *B'*. Come unica differenza rispetto a *B'*, *b'* al posto che un riferimento a *B* ha un riferimento a *b* (o un nuovo riferimento a *b*, nel caso *b* sia stato posto su un livello *l*=*L*+1). Tutti gli altri elementi e riferimenti del blocco *b'* sono uguali a quelli di *B'*.
4. Il precedente passaggio è ripetuto fino ad arrivare alla radice di T. La complessità dell'inserzione di un nuovo elemento è quindi Log_ *l*(*n*), cioè il numero di livelli del nuovo albero creato. Tale complessità corrisponde anche al numero di nuovi blocchi creati.

#### range
Sono iteratori facenti parte della classe delle sequenze. Un range è una sequenza rappresentante numeri interi equamente spaziati.

- ottenere un range di interi equispaziati con passo unitario, con estremo inferiore (incluso) e superiore (escluso) argomento: `<start> until <end>` (zucchero sintattico. Costruttore: `Range`)
- ottenere un range di interi equispaziati con passo unitario, con estremo inferiore (incluso) e superiore (incluso) argomento: `<start> to <end>` (zucchero sintattico. Costruttore: `Range`)
- ottenere un range di interi equispaziati con passo argomento, con estremo inferiore (incluso) e superiore (escluso) argomento: `<start> until <end> by <step>` (zucchero sintattico. Costruttore: `Range`)
- ottenere un range di interi equispaziati con passo argomento, con estremo inferiore (incluso) e superiore (incluso) argomento: `<start> to <end> by <step>` (zucchero sintattico. Costruttore: `Range`)

##### implementazione
Uno range è pensabile come una lista, con la differenza che le uniche informazioni allocate sono estremo inferiore, estremo superiore, e passo. Il prossimo elemento (prossimo intero) è valutato solo su richiesta.


### Stream

Sono iteratori facenti parte della classe delle sequenze.

- ottenere uno Stream associato ad una serie di oggetti argomento: `Stream(<objsSerie>)` (costruttore `Stream`)
- ottenere uno stream dalla collezione corrente: `this.toStream`

#### implementazione
Uno stream è pensabile come una lista, con la differenza che il prossimo elemento (tail) è dichiarato tramite lazy evaluation (e quindi valutato solo su richiesta e, se richiesto, una volta per tutte). A fronte di ciò, uno stream può avere lunghezza infinita.

#### Proprietà
Le proprietà degli stream sono come quelle delle liste, con alcune differenze.

##### Costruzione

- ottenere uno stream con head argomento e tail argomento: `<head> #:: <tail>` (il cancelletto specifica la lazy evaluation della coda. Tale notazione è utilizzabile anche per pattern matching)

##### metodi di trasformazione
Un metodo di trasformazione è un metodo che trasforma una collezione in una altra collezione (es: map, this.size, filter, reverse). Applicando un metodo di trasformazione ad uno stream, le trasformazioni specificate sono applicate ad un elemento solo quando questo è richiesto.

##### metodi non di trasformazione
E' necessario essere cauti nell'applicare un metodo di trasformazione (max, size, sum, toList) ad uno stream. Questi metodi richiedono di materializzare tutti gli elementi dello stream. Se l'allocazione in memoria dello stream richiede più spazio di quello disponibile, allora si ottiene una eccezione a runtime (in Scala: `java.lang.OutOfMemory`).


#### tuple
Sono strutture ordinate di dati eterogenei.

##### tuple literal
Un tuple literal la rappresentazione di una tupla all'interno del codice di un programma. Tale notazione è la più semplice per ottenere un valore tupla, e consiste nella notazione:
`(<e1>, ..., <en>)` equivale alla chiamata del costruttore tuple a n elementi `scala.Tuplen(<e1>, ..., <en>)`

##### accesso

###### named arguments
Data una tupla a n elementi, è possibile ottenere il valore dell'n-esimo elemento accedendone alla proprietà `this._<n>`.

###### destructuring
Data una tupla t a n elementi, è possibile effettuare destructuring tramite la sintassi:
`val (<x1>, ..., <xn>)=<t>`

#### Array
Sono sequenze di dati omogenei.

- ottenere un array contenente una serie di oggetti argomento: `Array(<objsSerie>)` (costruttore `Array`)
- ottenere un array dalla collezione corrente: `this.toArray`

##### proprietà
Sebbene non siano delle vere sottoclassi della classe delle sequenze (non per motivi concettuali, ma perchè sono collezioni native di Java, e non di Scala), gliarray supportano le stesse operazioni delle sequenze, e possono essere convertite implicitamente in sequenze se necessario.

#### Stringhe
Sono sequenze di code points.

- ottenere una stringa contenente i code points associati ad una serie di caratteri argomento: `"<chars>"` (string literal. Costruttore `String`)
- ottenere una stringa dal valore corrente: `this.toString`

##### proprietà
Sebbene non siano delle vere sottoclassi della classe delle sequenze (non per motivi concettuali, ma perchè sono collezioni native di Java, e non di Scala), gliarray supportano le stesse operazioni delle sequenze, e possono essere convertite implicitamente in sequenze se necessario. Alcune proprietà specifiche sono:

- ottenere true se la stringa comincia con una sottostringa argomento: `this startsWith <string>`

### set
Sono collezioni iterabili di oggetti unici.

- ottenere un set contenente una serie di oggetti argomento: `Set(<objsSerie>)` (costruttore `Set`). Se ci sono duplicati nella serie di oggetti solo uno tra di essi è inserito nel set.
- ottenere un set dalla collezione corrente: `this.toSet`

#### proprietà

##### ricerca e accesso

- ottenere true se un set argomento è subset del set corrente, false viceversa: `this contains <set>`

##### aggiunta/rimozione

- ottenere il set contenente gli elementi del set corrente più una serie di elementi argomento: `this + <val>` (per aggiungere un singolo elemento) o `this +(<valSerie>)` (per aggiungere una serie di elementi)
- ottenere un set dato dall'unione del set corrente e di un secondo set argomento: `this ++ <set>`
- ottenere un set contenente gli elementi del set corrente meno una serie di elementi argomento: `this - <val>` (per rimuovere un singolo elemento) o `this +(<valSerie>)` (per rimuovere una serie di elementi)
- ottenere un set dato dalla differenza tra il set corrente e un secondo set argomento: `this -- <set>`
- ottenere un set dato dall'intersezione tra il set corrente e un secondo set argomento: `this intersect <set>`
- ottenere un insieme vuoto dello stesso tipo del set corrente: `this.empty`


### Map
Sono collezioni iterabile consistente di coppie (chiave, valore), con chiavi che consistuiscono un set.

- ottenere una mappa contenente una serie di coppie (chiave, valore) argomento: `Map(<objsPairsSerie>)` (costruttore `Map`). objsPairsSerie è una serie di tuple `(<chiave>, valore>)`, che possono alternativamente essere espresse come `<chiave> -> <valore>` (zucchero sintattico). Il tipo associato ad una mappa è `Map[<keyType>, <valueType>]`, che estende la sottoclasse `Iterable[<keyType>, <valueType>]` e il tratto associato alle funzioni di tipo `<keyType> => <valueType>` (così che una mappa può essere fornita laddove una funzione è richiesta)
- ottenere una mappa dalla collezione di coppie corrente: `this.toMap`

#### proprietà

##### ricerca e accesso (query)

- ottenere il valore associato ad una chiave argomento nella mappa corrente, o una eccezione se la chiave argomento non è presente nella mappa: `this(<key>)` (mappa applicata alla chiave. Tale sintassi è ereditata dagli oggetti funzione, ed è zucchero sintattico per `this apply <key>`)
- ottenere un oggetto `Option` di sottotipo `Some` che wrappa il valore associato alla chiave argomento nella mappa corrente, o un oggetto Option `None` se la chiave argomento non è contenuta nella mappa argomento: `this get <key>`. Option è una case class, il che permette di utilizzare pattern matching gestendo i due possibili risultati (tipicamente con un case `None` per gestire l'assenza della chiave argomento nella mappa e un case `Some(<valuePattern>)` per gestire il caso di presenza della chiave argomento nella mappa e contestualmente utilizzare il valore associato in una opportuna espressione).
- ottenere il valore associato ad una chiave argomento nella mappa corrente, o un valore di default argomento se la chiave argomento non è presente nella mappa: `this getOrElse (<key>, <default>)`

##### aggiunta/rimozione

- ottenere una mappa con le stesse coppie (chiave, valore) della corrente mappa cui è aggiunta la coppia (chiave, valore) argomento: `this + (<key>, <val>)` (per aggiungere un singolo elemento) o `this + ((<keyA>, <valA>), (<keyB>, <val>)[, ...])` (per aggiungere una serie di elementi)
- ottenere una mappa con le stesse coppie (chiave, valore) della mappa corrente, più le coppie (chiave, valore) di una mappa argomento: `this ++ <map>`. Se una chiave della mappa argomento è già presente nella mappa corrente, allora il valore della mappa argomento è utilizzato.
- ottenere una mappa con le stesse coppie (chiave, valore) della corrente mappa cui è rimossa la coppia (chiave, valore) con chiave argomento: `this - <key>` (per una singola coppia) o `this - (<keySeries>)` (per rimuovere una serie di coppie)
- ottenere una mappa con tutte le coppie (chiave, valore) di una mappa argomento tranne quelle le cui chiavi sono contenute nella mappa corrente: `this -- <map>`

##### sottocollezioni

- ottenere un set contenente le chiavi della mappa corrente: `this.keys`
- ottenere un iterabile contenente tutti i valori associati ad una chiave nella mappa corrente: `this.valuesIterator`

##### HOF
Tra le HOF aggiuntive supportate dalle mappe vi sono:

- ottenere una mappa contenente solo le coppie (chiave, valore) associate alle chiavi della mappa corrente che soddisfano un predicato argomento: `this filterKeys <p>`
- ottenere una mappa contenente le coppie (chiave, valore) ottenute applicando una trasformazione argomento a tutti i valori associati ad una chiave della mappa corrente: `this mapValues <f>`


## Classi, tratti, oggetti

### Classi
Definiscono una categoria di oggetti, specificando cosa conoscono (variabili di istanza) e cosa sanno fare (metodi).

#### dichiarazione
La dichiarazione di una classe consiste di una intestazione e di un corpo. La sintassi dell'intestazione è:
`class <constructorId>'['<typeParsSerie>']'(<parSerie>){<body>}`
L'intestazione permette quindi di definire l'identificativo der il costruttore della classe, che per estesione sarà anche l'identificativo del tipo associato alla classe stessa, e la serie di parametri passati al costruttore. NB: Scala tiene gli identificativi di tipi e valori in namespace diversi, così che non può esserci conflitto tra i due.
Il corpo contiene una serie di dichiarazioni relative ai membri (espressioni) ed ai metodi (funzioni) propri delle istanza della classe definita. Siccome i membri sono definiti all'interno dello stesso blocco dei metodi, i metodi potranno utilizzare i membri senza bisogno di alcuna sintassi particolare (ambito lessicale).

#### costruttore

##### chiamata
La creazione di un nuovo oggetto di una data classe è effettuata all'interno del programma chiamando il relativo costruttore. La chiamata a un costruttore è effettuata tramite la sintassi:
`new <constructorId>'['<typeArgsSerie>']'(<valArgsSerie>)`
La valutazione della chiamata ad un costruttore è effettuata come la valutazione della chiamata ad una qualsiasi altra funzione.

##### self reference
All'interno del corpo del costruttore, l'oggetto `this` è un riferimento all'oggetto in fase di creazione. Per riferirsi a proprietà dell'oggetto creato non è indispensabile utilizzare il prefisso `this` (seppur spesso desiderabile per chiarezza). Tuttavia, l'utilizzo di `this` diventa indispensabile nel momento in cui si voglia utilizzare un riferiemento all'oggetto creato nella sua interezza.

##### costruttori ausiliari
In Scala, tramite la definizione di classe è implicitamente dichiarato un costruttore per la classe. Tale costruttore è detto *costruttore primario* ed è una funzione che  

- ha come parametri gli argomenti passati nella definizione di classe
- ha come espressione il corpo della definizione di classe.
Scala permette anche di definire altri costruttori, detti *costruttori ausiliari*. Un costruttore ausiliario deve essere dichiarato nel corpo del costruttore primario tramite la sintassi:
`def this'['<typeParsSerie>']'(<parSerie>)=<body>`
Un costruttore ausiliario:
- deve avere argomenti diversi rispetto al costruttore primario
- può svolgere operazioni diverse dal costruttore primario
E' possibile che un costruttore ausiliario chiami il costruttore primario o un altro costruttore ausiliario. Per chiamare un altro costruttore è sufficiete chiamare `this(<parSerie>)`

##### prerequisiti
All'interno del costruttore, è possibile definire delle condizioni inprescindibili affinchè una istanza della classe venga creata a seguito di una chiamata al suo costruttore (tipicamente controlli sui valori degli argomenti passati). A tale scopo, esiste la apposita funzione `require`.

- gettare una eccezione `IllegalArgumentException` nel caso una espressione di test argomento ritorna valore false: `require(<testExpression>[, "<messageString>"])`. Se l'argomento messageString è indicato, allora il testo della stringa è visualizzato nel caso in cui l'eccezione è gettata.

#### proprietà
Membri e metodi sono dichiarati all'interno del corpo del costruttore come normali espressioni e funzioni. Nel caso si voglia dichiarare un membro con valore uguale a quello di un parametro passato, è possibile effettuare tale operazione direttamente nella intestazione della classe prependendo la parola chiave val all'identificativo del parametro in questione (definendo contemporaneamente un paramentro e una proprietà).

##### accesso
Per selezionare membri e metodi è possibile utilizzare la:

- *dot notation*: dato il valore a con metodo o metodo m, è possibile valutare la proprietà m tramite la sintassi: `a.m`

Per selezionare un metodo è poi possibile anche utilizzare una tra le seguenti due notazioni:

- *infix notation*: dato il valore a con metodo m con un argomento, è possibile applicare m al valore b tramite la sintassi: `a m b`.
- *prefix notation*: dato il valore a con metodo m senza argomenti, è possibile applicare m al valore a tramite la sintassi: `m a`. Per utilizzare la prefix notation è necessario dare al metodo identificativo `unary_<method>`.

Notazioni infix e prefix, utilizzate unitamente ad identificatori simbolici per i metodi, permettono di utilizzare una notazione più concisa per le operazioni tra oggetti di una data classe creando degli operatori binari (infix) e unari (prefix). Tipicamente, tale notazione è utilizzata quando si desidera definire operazioni algebriche o logiche per gli oggetti della classe in questione o per identificare operazioni ricorrenti. Tuttavia, l'abuso di tali notazioni è sconsigliabile.

##### diritti
Di default, è possibile accedere a qualsiasi proprietà appartenente ad un oggetto. Per rendenre privata una proprietà (proprietà accessibile solo all'interno del corpo del costruttore di classe), è necessario far precedere la sua definzione dalla parola chiave `private`

#### ereditarietà
Per stabilire quando un tipo A può essere sottotipo di un tipo B, esiste il principio di Barbara Liskov:

>  Sia q(x) una proprietà provabile circa gli oggetti x di un tipo B. Allora, q(y) deve essere provabile per gli oggetti y del tipo A dove A <: B.

Tale concetto, espresso in termini più informali, afferma che, se A :< B, allora deve essere possibile effettuare tutto quello che è possibile effettuare con i valori del tipo B anche con i valori del tipo A. Intuitivamente, spesso questo risulta vero se un oggetto di A *è un* B.

##### estensione
Per estendere una classe creandone una *sottoclasse*, è necessario specificalo nella intestazione della sottoclasse stessa. La sintassi utilizzata per la dichiarazione della sottoclasse è la seguente:
`class <idSubclassConstructor> estends(<idSuperclassConstructor>){<subclassBody>}`
Estendendo una classe ne si ereditano tutti i membri e i metodi. Le *classi dirette* le *classi indirette* dalle quali si eredita sono anche dette *superclassi* (o *classi base*). Scala permette solo *eredità singola*, cioè una classe può avere una sola superclasse diretta. Il codice eseguito conseguentemente alla chiamata di un metodo dipende dal tipo a runtime dell'oggetto invocante (*dynamic method dispatch*).
Se una classe non estende esplicitamente un'altra classe, allora essa estende implicitamente la classe `Object`.

##### classi astratte
Una *classe astratta* è una classe della quale uno o più proprietà sono astratte, cioè mancano di implementazione. La dichiarazione di una proprietà astratta è effettuata riportandone solo la intestazione. La sintassi per la dichiarazione rispettivamente di un membro astratto e di un metodo astratto è la seguente:
`val <memberlId>: <memberType>`;
`def <methodId>'['<typeParsSerie>']'(<parSerie>) [: <returnType>]`, con tipo ritornato da indicare obbligatoriamente solo per funzioni ricorsive.
Classi astratte non sono istanziabili. Esse sono intese come dei 'template', delle classi modello astratte da estendere per creare *classi concrete*, istanziabili (o, eventualmente, ulteriori classi astratte intermedie). Perchè una classe che estende una superclasse astratta sia concreta (e quindi istanziabile), essa deve implementare tutti i membri e metodi non implementati della superclasse astratta.

##### Sovrascrittura e implementazione
Per sovrascrivere un membro o metodo ereditato da una superclasse che lo implementa, è necessario far precedere la sua definizione dalla parola chiave `override`. NB: se il membro o metodo ereditato necessita di implementazione (estensione di una classe astratta), allora non si ha *sovrascrittura* ma *implementazione*. In tale caso, la parola chiave override non è necessaria.

##### decomposizione
Un paradigma di decomposizione in programmazione è una strategia per organizzare un programma in un numero di parti. Tipicamente, lo scopo della decomposizione è ottimizzare un qualche parametro legato alla complessità del programma quale la sua modularità o mantenibilità.
Il paradigma di decomposizione orientato agli oggetti cerca di raggiungere decomposizione scomponendo un problema in una gerarchia di oggetti, ciascuno responsabile di parti sempre più specifiche del problema. Esso prevede di inserire metodi generici in una superclasse e implementare metodi specifici in ciascuna classe. Questo ha vantaggi nel caso si preveda di utilizzare tante classi in quanto permette una maggior modularità, sebbene abbia svantaggi in termini di verbosità del programma e di impossibilità di implementare dei metodi con logiche non locali. L'approccio contrario è quello di inserire nella superclasse un singolo metodo che agisca sulle istanze di un suo qualsiasi sottotipo effettuando controllo di tipo sugli argomenti. Questo ha vantaggi nel caso si utilizzino poche classi, rendendo il programma meno verboso ma centralizzando le funzionalità di tutte le classi nella superclasse

### Tratti
Un tratto in Scala è quanto di più analogo esisite alle interfacce di Java. Essi permettono di superare il limite della ereditarietà singola: una classe, oggetto o tratto può ereditare da una sola classe, ma da un numero arbitrario di tratti. La sintassi utilizzata per ereditare da un tratto è:
`class <classId> with <trait1Id> [with <trait2Id>][...]`

#### dichiarazione
Un tratto, similmente ad una classe, può contenere membri e metodi sia atratti che concreti (a differenza delle interfacce in Java, che possono avere solo membri e metodi astratti). Tuttavia un tratto, a differenza di una classe, può ricevere parametri di tipo ma non parametri di valore. La sintassi per la dichiarazione di un tratto è:
`trait <traitId> '['<typeParsSerie>']'{<body>}`

### Singleton objects
Anzi che creare una classe, è anche creare un singolo oggetto con determinate proprietà. Questo può tornare utile per evitare spreso di risorse nel caso in cui tutte le istanze che si creerebbero realizzando una classe sarebbero uguali. La sintassi utilizzata per la creazione di un singolo oggetto, è:
`object <objectId> {<body>}`

### Orientamento agli oggetti puro
Scala è un linguaggio orientato agli oggetti puro. Di conseguenza ogni tipo è una classe e ogni valore di un certo tipo è un oggetto di tale classe.
Nello specifico, in Scala:

- ogni tipo è una classe astratta o tratto. Tale classe dichiara i metodi principali di cui i valori del tipo rappresentato saranno dotati.
- ogni valore è un oggetto della classe che rappresenta il suo tipo. A seconda del valore in questione, si potranno avere delle diverse implementazioni dei metodi dichiarati nella relativa classe.
Questo discorso è valido anche per valori di tipi primitivi e funzioni. In particolare, per le funzioni:

- i tipi (A x n)=>B (da leggersi (A, A, .. n volte)=>B, con A e B generici) hanno ciascuno associato un tratto (per 0<=n<=22, oltre il quale non sono più definiti per motivi implementativi). Il corpo dell'n-esimo tratto definisce solo un metodo `apply` (A x n)=>B. Ad esempio il tratto relativo al tipo A=>B (n=1) sarà `trait FunctionN[A, B]{def apply(x: A): B}`.
- i valori function di un dato tipo sono delle istanze di una classe anonima che implementa l'opportuno tratto tra i 22 possibili specificando i parametri di tipo e l'implementazione del metodo apply. Quindi, un valore function è un oggetto di una opportuna classe che ne rappresenta il tipo, e con una proprietà apply che ne rappresenta il corpo.
Un'ulteriore nota è da fare sulle chiamate di funzione e sulle definizioni di funzione.

- quando è incontrata l'espressione f(x), con f oggetto, tale notazione è espansa in f.apply(x) (o, in altre parola, la sintassi <obj>(x) è zucchero sintattico per la sintassi <obj>.apply(x)). Quest'ultima è una espressione che viene valutata come descritto nel relativo paragrafo relativo alla valutazione di funzioni applicate.
Le definizioni di funzioni non sono a loro volta oggetti. Tuttavia, se l'identificativo di un metodo è trovato dove un valore function è atteso, esso è convertito automaticamente in un oggetto dell'opportuno tipo funzione.

###  Gerarchia delle classi built-in
La gerarchia delle classi built-in è la seguente:

- `scala.Any` è la classe al vertice della gerarchia e definisce il tipo base di tutti i tipi. In tale classe sono dichiarati i metodi più generali possibile: `==`, `!=`, `equals`, `hashCode`, `toString`, `isInstanceOf`, `asInstanceOf`, etc. scala.Any ha due sottoclassi dirette built-in:
  - `scala.AnyRef` è la classe base di tutti i reference types.
  - `scala.AnyVal` è la classe base di tutti i tipi primitivi (`Boolean`, `Char`, `Byte`, `Short`, `Int`, `Long`, `Float`, `Double`, `Unit`)

Ci sono due ulteriori classi in fondo alla gerarchia. Esse sono:

- `scala.Nothing`. La classe Nothing definisce il tipo `Nothing`, il quale è un sottotipo di ogni altro tipo (sia tipi primitivi che reference types, Null compreso). Non esistono valori di tipo Nothing; esso è utile per segnalare terminazione anormale del programma (tipo di una eccezione) o come tipo di una collezione vuota.
- `scala.Null`. La classe Null definisce il tipo `Null`, il quale è sottotipo di tutti i reference types. Tale classe ha un solo valore, con identificativo `null`. In virtù del polimorfismo, un valore null può essere passato ogni qual volta un valore di un qualsiasi reference type è atteso (ma non quando un valore di un tipo primitivo è atteso).

#### Test e conversione tipi
- ottenere true se un oggetto è una istanza di un tipo argomento, false viceversa: `<obj>.isInstanceOf[<T>]`. E' utile per creare metodi di superclassi chde agiscano in modo differente in base all'istanza ricevuta. E' un metodo deprecato in favore del pattern matching.
- effettuare conversione (cast) di un oggetto di un dato tipo in un tipo argomento: `<obj>.asInstanceOf[<T>]`. E' un metodo deprecato.

#### Sintassi tipi
La intassi utilizzata per dichiarare un parametro di tipo è la seguente:
`[+,-] <typeId>[:< <upperBoundType][:> <lowerBoundType>]`

- + o - possono essere utilizzati per indicare se l'entità che presenta il parametro di tipo dichiarato è covariante o anticovariante rispetto al parametro di tipo in questione.
- upperBoundType e/o lowerBoundType possono essere utilizzati per indicare un limite superiore e/o inferiore in termini di gerarchia dei tipi per il tipo rappresentato dal parametro di tipo in questione

#### Sintassi tipi di funzioni
Come per tutti gli altri argomenti, anche per le funzioni passate è necesssario indicarne il tipo. In Scala, il tipo di una funzione è espresso tramite la seguente sintassi:
`[(]<ParType1>[, <ParType2>],[...)] => <resultType>`
Se la funzione è a un solo argomento, allora le parentesi tonde possono essere omesse. Se vi sono argomenti che a loro volta sono funzioni, nel caso le parentesi siano omesse è sottointesa associatività da destra verso sinistra (es: Y => Y => Y è letto Y => (Y => Y)). Nel caso le funzioni passate non siano riutilizzate nel corpo del programma, è possibile ridurre ulteriormente la verbosità del programma passando le stesse sotto forma di funzioni anonime (ed evitando così di doverne effettuare la dichiarazione).
Una funzione è intesa accettare un qualsiasi valore appartenente al dominio. Se questo non avviene, cioè se una funzione è definita solo per alcuni valori del tipo indicato come dominio, si parla di *funzione parziale* (da non confondere con l'applicazione parziale delle funzioni. Un esempio di funzione parziale è ad esempio la divisione, che non accetta il valore 0). Il tipo di una funzione parziale è `PartialFunction[(]<ParType1>[, <ParType2>],[...)] <resultType>]`
E' possibile definire un alias per un tipo. Questo diventa particolamente utile per tipi funzione molto verbosi. Per effettuare ciò è usata la sintassi:
`type <alias> = <typeId>`

#### Sintassi tipi di tuple
Le tuple hanno una sintassi abbreviata per indicare il loro tipo.
`(T1, ..., Tn)` equivale al tipo parametrizzato `scala.tuplen[T1, ..., Tn]`

### Polimorfismo
*Polimorfismo*: provisione di una sola interfaccia per entità di tipi differenti.
Si distinguono principalmente due tipi di polimorfismo: polimorfismo parametrico e polimorfismo di sottotipo.

#### Polimorfismo parametrico
*Polimorfismo parametrico*: si ha polimorfismo parametrico quando il codice è scritto senza menzionare esplicitamente nessun tipo specifico, e quindi può essere utilizzato trasparentemente con un numero arbitrario di nuovi tipi. In OOP il polimorfismo parametrico è anche detto *generics* o *generic programming*, mentre in FP esso è generalemente abbreviato con *polimorfismo*.
In Scala è possibile sfruttare il polimorfismo parametrico tramite parametrizzazione dei tipi. La parametrizzazione dei tipi consiste nell'indicare, laddove un passaggio di parametri è richiesto, e cioè nelle dichiarazioni di funzioni e classi, non solo dei parametri di valore ma anche dei parametri di tipo. Analogamente, quando una funzione generica viene chiamata o una classe generica viene istanziata sarà necessario passare non solo gli argomenti di valore ma anche gli argomenti di tipo (sebbene questi ultimi possano spesso essere omessi in quanto il compilatore riesce a inferirli a partire dai tipi degli argomenti di valore passati (*type inference*))
Questo permette di creare funzioni e classi che possono ricevere argomenti di tipo diverso comportandosi allo stesso modo. La sintassi utilizzata per il passaggio dei parametri/argomenti di tipo consiste nell'indicare una serie di identificativi per i tipi tra `[]` prima di indicare la serie di parametri di valore tra `()`. Nello specifico, per il passaggio parametri, gli identificativi saranno identificativi generici poi usati per indicare i rispettivi tipi generici nella dichiarazione dei parametri di valore. Sebbene sia possibile utilizzare un qualsiasi nome per un identificativo generico, è convenzione utilizzare singole lettere maiuscole.

#### Polimorfismo di sottotipo
*Polimorfismo di sottotipo*: si ha polimorfismo di sottotipo  quando un identificativo denota molte classi differenti legate a una superclasse. In OOP il polimorfismo di sottotipo è generalmente abbreviato con *polimorfismo*, mentre in FP esso è anche detto *sottotipizzazione*.

#### Varianza
Sia C[T] un tipo parametrizzato, ed A e B due tipi tali che A :< B. Si hanno tre possibilità:

- C[A] <: C[B]. In tal caso, si dice che C è *covariante* rispetto al tipo T.
- C[A] >: C[B]. In tal caso, si dice che C è *controvariante* rispetto al tipo T.
- nè C[A] <: C[B] nè C[A] >: C[B] sono vere. In tal caso, si dice che C è *invariante* rispetto al tipo T.
A partire dal principio di Liskov, una funzione di tipo C[T, S]===T=>S, è contravariante rispetto al tipo dell'argomento T e covariante rispetto al tipo del risultato S. Tuttavia, per un generico tipo parametrizzato (es: tipo rappresentato da una classe parametrizzata), non è possibile stabilire una regola valida a priori.
Durante la compilazione, Scala effettua un controllo di varianza. A grandi linee, i controlli effettuati sono i seguenti:

- parametri di tipo covarianti possono apparire solo come tipi dei risultati dei metodi
- parametri di tipo controvarianti possono apparire solo come tipi dei parametri dei metodi
- parametri di tipo invarianti possono apparire dovunque

## Eccezioni
Una *eccezione* è un evento, il quale occorre durante l'esecuzione di un programma (a runtime), che ne interrompe il normale flusso di esecuzione. Da non confondere con un *bug*, che rappresenta un errore presente nel codice del programma.

### gettare
La sintassi utilizzata per gettare una eccezione è la seguente:
`throw <Error>`
Tale comando termina il processo di valutazione con l'eccezione Error. Una espressione non ha associato un valore, e il suo tipo è Nothing.
La classe base per le eccezioni è `Error`. Il costruttore di Error (e per eredità anche i costruttori delle sue sottoclassi built-in) prende un messaggio opzionale mostrato a console quando l'eccezione lanciata non è gestita. La sintassi per ottenere un oggetto di tipo Error è quindi:
`new Error([errorMessage])`

## Gerarchia progetti
La gerarchia di un progetto in ordinamento top-down è:

1. *progetto*
2. *pacchetto*. Per includere una entità gerarchicamente inferiore in un pacchetto, porre il predicato `package <packageId>` all'inizio del file in cui l'entità è dichiarato.
3. file, app, classe, oggetto, tratto, worksheet.

### importazione

#### fully qualified identifier
E' possibile riferirsi ad una entità tramite il suo identificativo completo (*fully qualified identifier*). L'identificativo completo di una entità è ottenuto come `[<packageId>.][<objectId>.]<entityId>`.

#### predicato import
In alternativa, è possibile *importare* una entità (pacchetto, classe, oggetto, proprietà di un oggetto, etc.) all'interno del programma, ponendo a inizio programma il predicato

- `import [<packageId>.][<objectId>.]<entityId>`. *Named import*. Permette di riferirsi all'entità entityId del pacchetto packageId (o oggetto objectId) semplicemente tramite l'identificativo <entityId>.
- `import [<packageId>.][<objectId>.]{<entity1Id>, <entity2Id>}`. *Named import*. Permette di riferirsi alle entità entity1Id ed entity2Id del pacchetto packageId (o oggetto objectId) semplicemente tramite gli identificativi <entity1Id> e entity2Id>.
- `import [<packageId>.][<objectId>.]_`. *Wildcard import*. Permette di riferirsi a qualsiasi entità del pacchetto packageId (o oggetto objectId) semplicemente tramite il suo identificativo.

#### importazioni automatiche
Alcune importazioni sono effettuate automaticamente, importando così tutte le entità built-in messe a disposizione da Scala. Esse sono:

- tutte le entità del pacchetto `scala`.
- tutte le entità del pacchetto `java.lang`
- tutte le entità del singleton object `scala.Predef`

### applicazioni standalone
Per creare una applicazione standalone è necessario scrivere un programma con un oggetto che possiede un metodo `main`. Tale metodo deve prendere come parametri una lista di stringhe e deve avere come tipo ritornato Unit. La sintassi della sua intestazione deve essere:
`def main(args :Array[String]): Unit`
Tale metodo main, sarà il primo metodo ad essere eseguito quando l'oggetto che lo contiene è valutato.

## Algoritmo di scrittura di un programma
Dato il compito che il programma è supposto svolgere, si scompone tale compito in una serie di compiti più semplici e, a loro volta, si scomporranno a cascata anche tali compiti in altri compiti ancora più semplici. Tale processo è seguito fino ad arrivare a dei compiti elementari.
E' scritta una funzione per ciascun compito. Tipicamente, i compiti non elementari saranno espletati da funzioni contenenti nel loro corpo le dichiarazioni delle funzioni utilizzate per portare a termine i sottocompiti in cui essi sono stati composti.

## test
### FunSuite
*FunSuite* è una classe che implementa una suite di test.
Per utilizzare la suite di test FunSuite, è necessario importare la classe FunSuite `org.scalatest.FunSuite` ed estenderla con una propria classe rappresentante uno specifico ambiente di test. All'interno del corpo della dichiarazione di classe, è possibile utilizzare una serie di metodi utili a implementare gli specifici test.

- dichiarare un test con un dato nome e registrarlo per l'esecuzione: `test("<testDescription>"){<testFunction>}`. testDescription è tendenzialmente utilizzato per descrivere lo scopo dello specifico test effettuato. testFunction è una funzione che effettivamente realizza il test. Il metodo test registra tale funzione per la successiva esecuzione. Per dichiarare un metodo un senza fornirne una implementazione utilizzare il metodo `pending` (senza argomenti) come testFunction.
- definire un test con un dato nome senza registrarlo per l'esecuzione: `ignore("<testDescription>"){<testFunction>}`.  testDescription è tendenzialmente utilizzato per descrivere lo scopo dello specifico test effettuato. testFunction è una funzione che effettivamente realizza il test. Il metodo ignore permette di dichiare un test, senza però registrarlo per la fase di esecuzione (es: utile per realizzare test di caratteristiche non ancora implementate)
- verificare all'interno di un test se una asserzione è vera: `assert(<assertion>)`. assertion è una espressione che ritorna un booleano. Se il booleano è falso, il test unitario è considerato fallito. Se nel test unitario è utilizzato l'operatore `===` per testare una uguaglianza, allora, in caso di test fallito, è anche stampato un breve report riguardo le motivazioni del fallimento del test.
- intercettare all'interno di un test un tipo di eccezione argomento potenzialmente gettata da una espressione argomento, e ottenerne il relativo oggetto: `intercept[<exceptionType>]{<expression>}`. L'oggetto ritornato può essere ispezionato all'interno di una istruzione di assert nel caso necessario.

## Eclipse
Integrated Development Environment (IDE) utilizzato principalmente per la programmazione in Java e Scala.

#### worksheet
Un worksheet è un foglio di lavoro nel quale il codice viene compilato ed eseguito ogni volta che il file è salvato. Il risultato dell'esecuzione di ogni espressione è mostrato a fianco della stessa sotto forma di commento.  
