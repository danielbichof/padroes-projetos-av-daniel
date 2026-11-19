# Avaliação Semestral - Padrões de Projetos

**Aluno:** Daniel | **RGM:** 41203861
**Linguagem:** Java 17+ (só biblioteca padrão)

## Estrutura

- `questao1/`: provisionamento multinuvem (`src/`, `diagrama/`, `config.properties`)
- `questao2/`: casa de leilões (`src/`, `diagrama/`)

## Padrões

**Questão 1:** Abstract Factory (`ProvedorFactory`, AWS e Azure) e Singleton (`Config`, `HistoricoAuditoria`).

**Questão 2:** Observer (lances), Strategy (comissão do vendedor) e Decorator (serviços extras na fatura).

## Como executar

```bash
cd questao1   # ou questao2
javac -d bin $(find src -name "*.java")
java -cp bin Main
```

No PowerShell, troque o `javac` por:

```powershell
javac -d bin (Get-ChildItem -Path src -Filter *.java -Recurse).FullName
```
