# CVERSO
Projetos do Curso ADA

Aplicação Monolítica de gerenciamento de cartões.


1. Descrição do problema 
-> Necessidade de uma plataforma que gerencie o uso de um cartão onde é verificado o número, senha e o saldo para uso.


2. Objetivo do sistema 
-> Autorizar transações de compra em cartões, validando a existência do cartão, verificando senha, status de ativação e saldo disponível. O sistema retorna se a compra foi aprovada ou negada, junto com o saldo restante em caso de aprovação.


3. Estilo arquitetural adotado
-> Aplicação monolítica 


4. Diagrama simples da arquitetura (imagem ou ASCII) 
->
┌─────────────────────────────────────────────────┐
│              CLIENTE (Postman/API)              │
└─────────────────┬───────────────────────────────┘
                  │ HTTP POST /api/compras
                  ▼
┌─────────────────────────────────────────────────┐
│          CAMADA DE APRESENTAÇÃO (REST)          │
│         AutorizadorController.java              │
│         - Recebe CompraDTO                      │
│         - Retorna ComprovanteDTO ou Erro        │
└─────────────────┬───────────────────────────────┘
                  │
                  ▼
┌─────────────────────────────────────────────────┐
│           CAMADA DE NEGÓCIO (Service)           │
│         AutorizadorService.java                 │
│         - Valida cartão, senha, saldo           │
│         - Processa compra                       │
│         - Gera comprovante                      │
└─────────────────┬───────────────────────────────┘
                  │
                  ▼
┌─────────────────────────────────────────────────┐
│         CAMADA DE DOMÍNIO (Entities)            │
│  ┌────────────────┐      ┌──────────────────┐  │
│  │  Cartao.java   │──────│ Transacao.java   │  │
│  │  - debitar()   │      │                  │  │
│  └────────────────┘      └──────────────────┘  │
└─────────────────┬───────────────────────────────┘
                  │
                  ▼
┌─────────────────────────────────────────────────┐
│      CAMADA DE PERSISTÊNCIA (Repository)        │
│  CartaoRepository      TransacaoRepository      │
│  (Spring Data JPA)                              │
└─────────────────┬───────────────────────────────┘
                  │
                  ▼
┌─────────────────────────────────────────────────┐
│           BANCO DE DADOS (H2 in-memory)         │
│         - Cartões                               │
│         - Transações                            │
└─────────────────────────────────────────────────┘


5. Justificativa das decisões arquiteturais 
-> 5.1. Arquitetura Monolítica
Simplicidade: Para o escopo do projeto (gerenciamento de cartões), uma arquitetura monolítica é mais simples de desenvolver e manter
Performance: Toda comunicação entre camadas é local (sem acessar outros recursos de rede)
Facilidade de deployment: Um único artefato executável
5.2. Separação em Camadas
Controller: Isola lógica HTTP/REST da lógica de negócio
Service: Centraliza as regras de negócio e coordena operações
Repository: Abstrai acesso a dados usando Spring Data JPA
DTOs: Desacoplamento entre representação externa (API) e interna (entidades)
5.3. Rich Domain Model
Método debitar() no Cartao encapsula regra de validação de saldo
Melhor coesão e menor acoplamento
5.4. Tecnologias Escolhidas
Spring Boot: Framework robusto, produtivo e com vasta comunidade
H2 Database: Banco em memória, ideal para desenvolvimento e testes
JPA/Hibernate: ORM padrão Java, facilita persistência
5.5. Transações e Consistência
Anotação @Transactional garante atomicidade: ou toda operação é realizada (débito + registro de transação) ou nada é persistido


6. Instruções para execução do projeto 
->
Pré-requisitos:
Java 17 ou superior
Maven 3.6+
-> Executar:
mvn clean compile
mvn spring-boot:run


