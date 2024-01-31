# NTA - Network Analyser

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
