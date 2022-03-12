# Criando um Banco Digital com Java e Orientação a Objetos

Descrição do desafio:
Considerando o nosso conhecimento no domínio bancário, iremos abstrair uma solução Orientada a Objetos em Java.<br/> 
Para isso, vamos interpretar o seguinte cenário: <br/> “Um banco oferece aos seus clientes dois tipos de contas (corrente e poupança), as quais possuem as funcionalidades de depósito, saque e transferência (entre contas da própria instituição).”

O objetivo do lab era explorar os pilares da orientação a objetos, abstração, encapsulamento, herança e poliformismo.

Foram sugeridas algumas melhorias como tarefa adicional como criar mais classes atributos e metodos
e adicionar o lombok.

Como o foco era a orientação a objeto eu preferi não adicionar um banco de dados ou outra forma de armazenamento dos dados, basicamene o que fiz foi adicionar uma interface cli ao programa, adicionar o lombok e a criação de testes unitários. Existem diversas melhorias que poderiam ser feitas, como as classes de comando não possuem testes unitários, não existe uma listagem. Para avançar no bootcamp eu preferi deixar essas coisas para fazer depois com mais calma, mas para demonstrar que entendi bem o conteúdo, o que foi feito até aqui é suficiente.

### Dependências
* Java SDK 11

### Compilar
No terminal, no dirtório raiz do projeto, digite:

    ./gradlew jar

O arquivo ".jar" será criado no direrório:

    build/libs/

### Utilização
Execute com o java:

    java -jar build/libs/banco-digital-oop-1.0-SNAPSHOT.jar

Com o programa aberto, podem ser executados os seguintes comandos:

    ◌ depositar <numero conta> <valor>
	    Deposita na conta <numero conta> o valor <valor>(formato decimal 0.0) especificado.

    ◌ sacar <numero conta> <valor>
        Saca da conta <numero conta> o valor <valor>(formato decimal 0.0) especificado.
    
    ◌ extrato <conta numero>
        Exibe o extrato da conta <conta numero>.

    ◌ transferir <numero conta origem> <numero conta destino> <valor>
        Transfere da conta de origem para a conta destino o valor (formato decimal 0.0) especificado.

    ◌ abrir conta <tipo>
        Abre uma conta do <tipo> especificado (poupança ou corrente).

    ◌ ajuda
        Exibe comandos para o programa.

    ◌ sair
        Finaliza o programa.

### Tecnologia utilizada
 * Java 11
 * Lombok
   * Utilizado para diminuir a quantidade de código necessário para criação de classe, principalmente para evitar a necessidade de escrever métodos de acesso para a propriedades privadas. 
 * Junit Jupiter
   * Utilizado para facilitar a criação de testes automatizados para as classes criadas.
 * AssertJ
   * As instruções de asserção desta biblioteca são de mais facil entendimento do que as que vem por padrão no framework Junit.
 * Mokito
   * Para que cada teste unitário seja efetivo e o ideal é que teste apenas a funcionalidade da classe em questão, o mokito serve para simular o comportamento das classes das quais o objeto sendo testado depende. 