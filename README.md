## Trabalho-TCP

**Exercício	de	Programação	Distribuída:	Sistema	de	Busca	de	Produtos**

Construa	um	sistema	distribuído	de	busca	de	produtos,	conforme	mostra	a	figura	abaixo,	com	
os	seguintes	tipos	de	processos:

1. **Consumidor**:	implementa	uma	interface	humano-computador	que	possibilita	um	
usuário anônimo realizar	buscas	de	produtos.	Em	cada	busca,	o	usuário	deve	fornecer	
um	texto	parcial (substring)	do	título	do	produto	desejado. Como	resposta	a	uma	
busca,	o	usuário	visualiza	a lista	de	produtos	disponíveis	em	cada	uma das lojas	que	
compõem	o	sistema. Por exemplo,	se	o	usuário	fornecer	o	texto	"geladeira"	como	
entrada,	poderá	receber	a	seguinte	resposta:

        Geladeira Consul 500 litros, Lojas Americanas, 2500.00
        Geladeira Brastemp 440 litros, Casas Bahia, 3800.00
        Geladeira Electrolux 380 litros, Casas Bahia, 2100.00
        Geladeira Electrolux 380 litros, Magalu, 2300.00
        Geladeira Brastemp 400 litros, Magalu, 3700.00

2. **Servidor	de	Busca	de	Produtos**:	responde	as	solicitações	de	busca	feitas	pelos	
consumidores.	O	servidor	mantém	um	catálogo	de	produtos,	sendo	que,	para	cada	
produto,	há	os	seguintes campos:	nome	da	loja (texto),	título (texto)	e	preço (número	
real). Como	resposta	a	um	consumidor,	o	servidor	gera	uma	lista	de	produtos	que	
satisfaz	o	texto	parcial	fornecido para	o	título,	isto	é,	os	produtos	cujos	títulos	contêm	
o	texto	parcial.	Cada	busca	realizada	é	devidamente	registrada	pelo	servidor,	
constando	a	data	(dia	e	hora)	da	busca,	o	identificador	único da	busca (pode	ser	um	
número	sequencial	gerado	pelo	próprio	Servidor),	o	texto	parcial	utilizado e	a	
quantidade	de	produtos	retornados	por	cada	loja.  
  
1. **Administrador**:	implementa	uma	interface	humano-computador	que	possibilita	um	
usuário	administrador	do	sistema visualizar	o	histórico	de	buscas	realizadas	pelo	
servidor.