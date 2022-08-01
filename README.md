# Nosso Ingresso Agora

## Dicas

1. Baixe ou Clone este projeto em um local em sua maquina, onde você possa continuar com este codigo, pois existem mais de uma atividade que o utiliza como base.
2. Importe na sua IDE Favorita, caso não conheça sobre o IDE Java, recomendo o uso do IntelliJ Comunity.
3. Siga a descrição da atividade que estiver fazendo para construção da funcionalidade pedida.
4. Qualquer Duvida entre em contato com o Time de Mentores da Zup Edu.

## API

### Eventos

```shell
curl --request POST \
  --url http://localhost:8080/eventos \
  --header 'Content-Type: application/json' \
  --data '{
	"titulo":"Show da xuxa",
	"data":"05-10-2022",
	"preco":25.00
}'
```

### Clientes

```shell
curl --request POST \
  --url http://localhost:8080/clientes \
  --header 'Content-Type: application/json' \
  --data '{
	"nome":"Ana Luiza Sousa",
	"email":"apsousa10@gmail.com",
	"nascimento":"15-02-2000"
}'
```

### Ingressos

```shell
curl --request POST \
  --url http://localhost:8080/ingressos \
  --header 'Content-Type: application/json' \
  --data '{
	"idCliente":1,
	"idEvento":1
}'
```