# NTA - Network Analyser

![Descrição da imagem](url)

Esse aplicativo permite realizar uma vários de testes de conectividade e validações de rede. Foi desenvolvido em Java, deste modo, utiliza a JVM da estação para processar suas ações, o que a torna semelhante aos produtos da Philips, como o Philips Application Manager e o Tasy (HTML5/Java), ambos escritos em Java. Sua interface foi projetada em formato de console, para execução tanto em estações locais quanto em servidores que hospedam o Tasy ou outros aplicativos web da Philips, assim como em máquinas que hospedam o banco de dados. O propósito é permitir a realização de testes de conectividade para identificar problemas em ambientes dos clientes. A aplicação, dentro do contexto específico de cada módulo, gera um log que evidencia problemas, mesmo que esteja fora do sistema Tasy.

## Features

- ClassLoadingMXBean
- CompilationMXBean
- GarbageCollectorMXBean
- MemoryMXBean
- OperatingSystemMXBean
- ThreadMXBean
- Time
- OPTS
- Java_home
- Todas as informações (*)
- Database\Oracle
- Database\MYSQL
- Database\Microsoft SQL Server
- SMB
- SMTP
- SOCKET
- HTTP Post Request
- SMB Leitura e escrita (SMBRW)
- XML

Como o aplicativo utiliza um .JAR (Java Archive) ele pode ser executado em qualquer sistema operacional que tenha uma Maquina Virtual Java (JVM) compatível, ou seja, pode ser utilizando em um sistema Windows ou Linux comumente utilizado pelo mercado, o código fonte foi desenvolvido para detectar o sistema operacional.

## Tipos arquivos
Há dois scripts disponíveis: um denominado 'start.bat' projetado para ser utilizado em sistemas operacionais Windows, e outro chamado 'start.sh' destinado a sistemas operacionais Linux. Ambos os scripts iniciam a execução do arquivo 'networkTester.jar', que encapsula todas as classes principais do aplicativo. É importante observar que há uma dependência em relação às bibliotecas externas localizadas na pasta '/lib'. Essas bibliotecas devem ser obrigatoriamente colocadas no diretório raiz para garantir o correto funcionamento do aplicativo.

## Comandos
O processo envolve duas telas fundamentais: a primeira destinada à seleção dos módulos e a segunda voltada para a parametrização desses módulos.
![Descrição da imagem](https://imgur.com/SXpZlP2)

| Comando | Descrição |
| ------ | ------ |
| 'help' ou 'hp' | Exibe uma breve descrição sobre os módulos. |
| 'load' | Carrega uma biblioteca de módulo. |
| 'exit' ou 'x' | Encerra o console. |

![Descrição da imagem](https://imgur.com/xchptYP)


| Comando | Descrição |
| ------ | ------ |
| 'help' ou 'hp' | Exibe uma breve descrição sobre os módulos. |
| 'set' | Define um valor a uma váriavel. |
| 'unset' | Exibe uma breve descrição sobre os módulos. |
| 'run' | Executa o módulo. |
| 'exit' ou 'x' | Retorna do contexto atual. |
| 'exit' ou 'x' | Encerra o console. |

## Parâmetros/Variáveis
Ao utilizar o comando ‘run’ todas as variáveis obrigatórias para a execução do módulo serão utilizadas, porém, é necessário fornecê-las previamente.

Para atribuir um valor a uma variável, utilize o comando a seguir:
> set <variável> <valor>

Caso deseje limpar o valor de uma variável, utilize o comando:
> unset <variável>

![Descrição da imagem](https://imgur.com/4q3Xez3)

## Lista de variáveis globais:

| Comando | Modulo | Descrição |
| ------ | ------ | ------ |
| 'host' | SMTP/SMB/SOCKET | Máquina/servidor.
| 'port' | 	SMTP/SOCKET | Porta do servidor.
| 'aut' | SMTP | Requer autenticação.
| 'stls' | SMTP | 	Utiliza STARTTLS.
| 'prot' | SMTP | 	Protocolo.
| 'rem' | SMTP | Remetente.
| 'des' | SMTP | Destinatário.
| 'pwd' | SMTP/SMB/DATABASE | 	Senha.
| 'url' | 	HTTP POST REQUEST/DATABASE | 	URL (Endereço).
| 'data' | 	HTTP POST REQUEST | Dados a serem enviados ao servidor.
| 'usr' | 	SMB/SMTP/DATABASE | 	Usuário.
| 'tmsg' | SMTP | 	Titulo do e-mail.
| 'pmsg' | SMTP | 	Corpo do e-mail.
| 'qtdm' | SMTP | 	Quantidade e-mails a serem enviados.
| 'dmn' | SMB | Dominio.

## Parâmetros obrigatórios (*):
Todos os parâmetros marcados com (*) são obrigatórios. Caso não sejam informados, a execução do módulo por meio do comando 'run' não será possível.

![Descrição da imagem](https://imgur.com/f9UbGjR)

## Módulos:
Para visualizar todos os módulos disponíveis, você pode utilizar o comando 'hp' ou 'help'. Os módulos são categorizados por bibliotecas, seguidos pelo nome do respectivo módulo.

![Descrição da imagem](https://imgur.com/Se4Ttfx)

## Carregar um módulo:
Para carregar um módulo, utilize o seguinte comando:
> load <nome do módulo>

![Descrição da imagem](https://imgur.com/Uq0Jeg9)

## JVM INFO:
Nesta seção, encontram-se módulos que fazem uso da biblioteca 'java.lang.management' para apresentar em tela aspectos relevantes da Máquina Virtual Java (JVM). Esses módulos são destinados ao monitoramento de desempenho de aplicativos Java

* Não é necessário informar nenhum parâmetro.

## ClassLoadingMXBean:
![Descrição da imagem](https://imgur.com/xCSrJzL)
Exibe o número total de classes carregadas e descarregadas atualmente.

## CompilationMXBean:
![Descrição da imagem](https://imgur.com/LrZ7jy4)
Exibe informações sobre o sistema de compilação Just-In-Time (JIT) da JVM.
![Descrição da imagem](https://imgur.com/NrEGClv)
## GarbageCollectorMXBean:
![Descrição da imagem](https://imgur.com/bC5DLPY)
Exibe informações sobre ‘GarbageColletor’ da JVM, como nome do coletor, como tempo gasto em memória e quantidade memória coletada.
![Descrição da imagem](https://imgur.com/wkh8LXw)
## MemoryMXBean:
![Descrição da imagem](https://imgur.com/v7pMsjs)
Exibe informações de quantidade inicial de memória alocada para a JVM, seguida da quantidade de memória usada pela JVM, quantidade de memória, quantidade de memória alocada pela JVM e que está pronta para ser usada e por fim a quantidade máxima de memória que a JVM pode alocar, todos expressos em bytes e em kilobytes (entre parênteses).
![Descrição da imagem](https://imgur.com/zwuEpht)
## OperatingSystemMXBean:
![Descrição da imagem](https://imgur.com/i5fYBiP)
Exibe o sistema operacional reconhecido pela JVM.
![Descrição da imagem](https://imgur.com/EGQeDf6)
## ThreadMXBean:
![Descrição da imagem](https://imgur.com/L5p5075)
Exibe informações sobre Threads ativas no momento na JVM e o pico de Threads que foram alcançadas durante a execução.
![Descrição da imagem](https://imgur.com/HmkrA1t)
## Time:
![Descrição da imagem](https://imgur.com/PDMz8Jc)
Exibe o horário obtido da JVM e o horário obtido do sistema operacional:
![Descrição da imagem](https://imgur.com/WbK8TRD)
É útil em casos em que o horário do sistema desenvolvido em Java está incorreto, muitas vezes é causado pelos da parâmetros de JVM, conforme exemplo acima.
## OPTS:
![Descrição da imagem](https://imgur.com/rxYai68)
Exibe parâmetros de JVM (Quando reconhecidos).
![Descrição da imagem](https://imgur.com/ogkrKBq)
## Java_home:
![Descrição da imagem](https://imgur.com/lKApnH4)
Exibe o diretório de instalação do Java que está executando o aplicativo atual.
![Descrição da imagem](https://imgur.com/6wDu6Yv)
## Todas as informações (*):
Exibe todas as informações possíveis relacionadas a JVM:
![Descrição da imagem](https://imgur.com/Uu0v56j)
## Database\Oracle:
![Descrição da imagem](https://imgur.com/yyx6kkC)
![Descrição da imagem](https://imgur.com/HOr7gOH)
Estabelece conexão com um banco de dados Oracle utilizando o driver da proprietária.
### Parâmetros:
| Comando | Descrição |
| ------ | ------ |
| 'url' | Endereço do banco de dados. |
| 'usr' | Usuário com acesso ao banco de dados. |
| 'pwd' | Senha de acesso ao banco de dados. |
* A URL deve seguir o seguinte formato: jdbc:oracle:thin:@//localhost:1521/SEU_SERVICO_OU_SID
* A porta padrão para bancos de dados Oracle é 1521.

### Casos de uso:
![Descrição da imagem](url)
![Descrição da imagem](url)

## Database\MYSQL:
Estabelece conexão com um banco de dados MySQL utilizando o driver específico da provedora, seguindo a mesma lógica abordada no item Database\Oracle.
![Descrição da imagem](url)
![Descrição da imagem](url)
### Parâmetros:
| Variável | Descrição |
| ------ | ------ |
| 'url' | Endereço do banco de dados. |
| 'usr' | Usuário com acesso ao banco de dados. |
| 'pwd' | Senha de acesso ao banco de dados. |
* A URL deve seguir o seguinte exemplo: jdbc:mysql://localhost:3306/my_database
* A porta padrão para banco de dados MYSQL é a 3306. 
* As mesmas configurações podem ser utilizadas para bancos de dados MariaDB, uma vez que é um fork do MySQL.


## Database\Microsoft SQL Server:
Estabelece conexão com um banco de dados Microsoft SQL Server utilizando o driver da proprietária, segue a mesma lógica abordada no item Database\Oracle.
![Descrição da imagem](url)
![Descrição da imagem](url)
### Parâmetros:
| Variável | Descrição |
| ------ | ------ |
| 'url' | Endereço do banco de dados. |
| 'usr' | Usuário com acesso ao banco de dados. |
| 'pwd' | Senha de acesso ao banco de dados. |
* A URL deve seguir o seguinte exemplo: jdbc:sqlserver://localhost:1433;databaseName=nome_basededados
* A porta padrão para banco de dados Microsoft SQL Server é a 1433.

## SMB:
![Descrição da imagem](url)
![Descrição da imagem](url)
Estabelece uma conexão SMB (Server Message Block) com um servidor de arquivos.
| Variável | Descrição |
| ------ | ------ |
| 'host' | Endereço do servidor de arquivos. |
| 'usr' | Nome do usuário para autenticação do compartilhamento de rede. |
| 'pwd' | Senha do usuário para autenticação do compartilhamento de rede. |

* O usuário e senha pode ser encontrado no Tasy HTML5 em Administração do sistema > Parâmetros função > Menu do sistema > Parâmetro [185] - Autenticação para acesso ao ambiente de rede. 
* A máquina (servidor) e diretório podem ser encontrados no Tasy HTML5 em Administração do sistema > Parâmetros > Armazenamento de arquivos.

### Obtendo os parâmetros necessários do Tasy:
![Descrição da imagem](url)
![Descrição da imagem](url)
### Casos de uso:
![Descrição da imagem](url)
![Descrição da imagem](url)

## SMTP:
![Descrição da imagem](url)
![Descrição da imagem](url)
Estabelece uma conexão com um servidor de e-mails através do protocolo SMTP (Simple Mail Transfer Protocol).
### Parâmetros obrigatórios (*):
| Variável | Descrição | Parâmetro do Tasy (Menu do sistema) |
| ------ | ------ | ------ |
| 'host' | Endereço do servidor de arquivos. | Parâmetro [20] - Nome do servidor de Mensagens enviadas; 
| 'port' | Nome do usuário para autenticação do compartilhamento de rede. | Parâmetro [109] - Informe a porta a ser utilizada para envio de e-mail; 
| 'prot' | Senha do usuário para autenticação do compartilhamento de rede. | Parâmetro [125] - Método de autenticação SSL (e-mail); 
| 'rem' | Nome do usuário para autenticação do compartilhamento de rede. | Parâmetro [38] - Nome do USERID do servidor de E-mail; 
| 'pwd' | Senha do usuário para autenticação do compartilhamento de rede. | Parâmetro [40] - Senha para autenticação do SMTP do envio de e-mails;

### Parâmetros opcionais:
Os valores das variáveis abaixo são opcionais, ou seja, não é obrigatório serem informadas, já existe um valor padrão:
| Variável | Descrição | Valor padrão | Parâmetro do Tasy (Menu do sistema)
| ------ | ------ | ------ | ------ |
| 'Des' | Destinatário do e-mail. | Preenchido automaticamente ao informar o ‘rem’ (Des = rem); | NA
| 'Tmsg' | Título do e-mail. | Email sent with a default title by the NTA. | NA
| 'Pmsg' | Assunto do e-mail. | Email sent with a default subject by the NTA. | NA
| 'Qtdm' | Quantidade de e-mails a serem disparados. | 1 | NA
| 'Aut' | Utiliza autenticação para envio de e-mails. | y | Parâmetro [96] - Utiliza autenticação para envio de e-mails;
| 'Stls' | Emitir comando STARTTLS. | y | Parâmetro [110] - Utiliza envio de e-mail através do protocolo SSL;
### Obtendo os parâmetros necessários do Tasy:
![Descrição da imagem](url)
### Protocolos aceitos:
| Protocolo | Descrição | Versão do Java (Requisito) |
| ------ | ------ | ------ |
| 'SSLv2' | Secure Sockets Layer | Versões iniciais do Java.
| 'SSLv3' | Secure Sockets Layer | Versões iniciais do Java.
| 'TLSv1.0' | Transport Layer Security | 	Java ≥6
| 'TLSv1.1' | Transport Layer Security | 	Java ≥7
| 'TLSv1.2' | Transport Layer Security | Java ≥7
| 'TLSv1.3' | Transport Layer Security | Java ≥11
### Casos de uso:
![Descrição da imagem](url)
![Descrição da imagem](url)
* Em alguns casos a própria biblioteca vai informar uma documentação completa da provedora sobre o erro obtido, conforme exemplos.

![Descrição da imagem](url)
![Descrição da imagem](url)
![Descrição da imagem](url)
![Descrição da imagem](url)
![Descrição da imagem](url)
![Descrição da imagem](url)
* Em alguns casos mesmo informando todas as parametrizações de autenticação e do servidor de e-mails corretamente, algumas provedoras solicitam o uso de Senha de APP.

![Descrição da imagem](url)

## SOCKET:
![Descrição da imagem](url)
![Descrição da imagem](url)

Estabelece comunicação entre um cliente e servidor. Socket fornece uma abstração de mais baixo nível das operações de rede, tornando o desenvolvimento de software mais simplificado sem a necessidade de conhecer a fundo redes de computadores.
### Parâmetros:
| Variável | Descrição |
| ------ | ------ |
| 'host' | Servidor destino. |
| 'port' | Porta do servidor. |

### Portas padrões em redes de computadores:
| Variável | Descrição | Porta |
| ------ | ------ | ------ |
| 'HTTP' | Hypertext Transfer Protocol | 80 |
| 'HTTP (Seguro)' | Hypertext Transfer Protocol Secure | 443 |
| 'SMTP' | 	Simple Mail Transfer Protocol | 25 |
| 'POP3' | Post Office Protocol version 3 | 110 |
| 'IMAP' | Internet Message Access Protocol | 143 |
| 'SMTP (Seguro)' | Simple Mail Transfer Protocol | 465 e 587 |
| 'FTP' | File Transfer Protocol | 21 |
| 'SSH' | 	Secure Shell | 22 |

### Casos de uso:
![Descrição da imagem](url)
## HTTP Post Request:
![Descrição da imagem](url)
![Descrição da imagem](url)
Realiza uma requisição utilizando o protocolo HTTP com o método Post em um servidor. O método POST é utilizado pelos navegadores ou aplicativos para enviar dados a um servidor.
### Parâmetros:
| Váriavel | Descrição |
| ------ | ------ |
| url | Endereço URL para qual a requisição será feita.  |
| data | Dados a serem enviados ao servidor. |

### Casos de uso:
![Descrição da imagem](url)
Nesse exemplo o servidor recebe a solicitação, mas não terá dados para processar. O comportamento para esse tipo de requisição dependerá de como foi feito a implementação do servidor e da aplicação que está sendo acessada, neste caso, o servidor apenas retornou o corpo HTML, em outros casos pode apenas retornar um status 200 (OK), indicando que a requisição foi bem-sucedida.
![Descrição da imagem](url)
![Descrição da imagem](url)
* Nesse exemplo é enviado para o servidor como dados para serem processados o projeto do Appmanager “TasyEMR”, mas pode ser utilizado outros projetos (serviços da Philips), como por exemplo o “HealthProfessional”, “TasyJava”, “TasyReports”, “TasySchedulerWeb”, “Patient”, “ResultsPortal”, etc.

![Descrição da imagem](url)
* O erro acima geralmente acontece quando o sistema não consegue construir uma cadeia de certificação confiável até o certificado raiz, sendo necessário atualizar os certificados no Java Keystore, usando a ferramenta “Keytool”.

![Descrição da imagem](url)
![Descrição da imagem](url)
![Descrição da imagem](url)
![Descrição da imagem](url)
![Descrição da imagem](url)
![Descrição da imagem](url)

## SMB Leitura e escrita (SMBRW):

![Descrição da imagem](url)
![Descrição da imagem](url)
Estabelece uma conexão SMB (Server Message Block) com um servidor de arquivos, envia um arquivo de texto para o diretório, realiza a leitura desse mesmo arquivo de texto e retorna essa informação ao cliente para validação de leitura e escrita do diretório utilizando o usuário e senha informados no parâmetro.

### Parâmetros:
| Váriavel | Descrição |
| ------ | ------ |
| host | Endereço URL para qual a requisição será feita.  |
| dmn | Domínio para autenticação do compartilhamento de rede. |
| usr | Nome do usuário para autenticação do compartilhamento de rede. |
| pwd | Senha do usuário para autenticação do compartilhamento de rede. |

### Casos de uso:
![Descrição da imagem](url)
* Ao rodar o módulo, será enviado um arquivo de texto ao servidor, o arquivo de texto terá o nome padrão “smbRW+(dd-MM-yyyy-HH:mm:ssss)”, servindo como um identificador único para o nome do arquivo. Se o envio de arquivo ocorrer corretamente, na sequência haverá a tentativa de leitura do arquivo de texto e será retornado ao cliente o texto do arquivo de dentro do servidor, utilizando as configurações do usuário e senha pertencentes ao domínio.



## XML:
O Tasy permite a exportação dos Parâmetros do menu do sistema para um arquivo no formato .XML, a ultima funcionalidade feita para o aplicativo foi que ele permitisse importar esses parâmetros XML, atribuindo as variáveis do APP, sendo necessário transferir o arquivo .XML para o diretório ‘\NTA\class\xml’ e na sequência utilizar o comando abaixo:
> load xml

### Etapas:
![Descrição da imagem](url)
![Descrição da imagem](url)
![Descrição da imagem](url)
