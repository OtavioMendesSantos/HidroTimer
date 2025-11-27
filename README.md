# 💧 HidroTimer

<div align="center">
  <img src="app/src/main/res/drawable/logo.png" alt="HidroTimer Logo" width="250"/>
  
  **Aplicativo de rastreamento de hidratação para Wear OS**
</div>

---

## 📱 Sobre o Projeto

O **HidroTimer** é um aplicativo desenvolvido para dispositivos Wear OS (relógios inteligentes) que ajuda os usuários a manterem-se hidratados ao longo do dia. O app permite registrar a quantidade de água ingerida, definir uma meta diária personalizada e acompanhar o progresso em tempo real.

### Principais Funcionalidades

- ✅ **Registro de Água**: Adicione a quantidade de água ingerida em mililitros
- ✅ **Meta Diária**: Defina e ajuste sua meta de hidratação diária
- ✅ **Acompanhamento em Tempo Real**: Visualize o total ingerido e o progresso em relação à meta
- ✅ **Reset Automático Diário**: O contador é resetado automaticamente a cada novo dia
- ✅ **Notificação de Meta**: Feedback visual quando a meta diária é atingida
- ✅ **Easter Egg**: Funcionalidade secreta descoberta ao clicar 10 vezes no copinho
- ✅ **Interface Otimizada**: Design pixel art otimizado para telas de relógio

---

## 🏗️ Estrutura do Projeto

```
HidroTimer/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/senac/hidrotimer/
│   │       │   ├── data/                    # Camada de dados
│   │       │   │   ├── AguaDao.kt          # DAO para operações de água
│   │       │   │   ├── MetaDao.kt          # DAO para operações de meta
│   │       │   │   ├── HidroTimerDatabase.kt  # Configuração do Room Database
│   │       │   │   └── HidroTimerRepository.kt # Repositório (camada de abstração)
│   │       │   ├── model/                   # Modelos de dados
│   │       │   │   ├── Agua.kt             # Entidade de água ingerida
│   │       │   │   └── Meta.kt              # Entidade de meta diária
│   │       │   └── presentation/           # Camada de apresentação
│   │       │       ├── MainActivity.kt      # Activity principal
│   │       │       ├── HidroTimerViewModel.kt  # ViewModel (lógica de negócio)
│   │       │       ├── HomeScreen.kt        # Tela principal
│   │       │       ├── AddWaterScreen.kt     # Tela de adicionar água
│   │       │       ├── AlterarMetaScreen.kt # Tela de alterar meta
│   │       │       ├── MetaAtingidaScreen.kt # Tela de meta atingida
│   │       │       ├── SplashScreen.kt      # Tela de splash
│   │       │       ├── EasterEggScreen.kt   # Tela do easter egg
│   │       │       └── theme/               # Tema e tipografia
│   │       ├── res/                         # Recursos (imagens, layouts, etc.)
│   │       │   ├── drawable/               # Imagens e drawables
│   │       │   ├── mipmap-*/               # Ícones do aplicativo
│   │       │   └── values/                 # Strings, cores, temas
│   │       └── AndroidManifest.xml          # Manifesto do Android
│   └── build.gradle.kts                     # Configuração do módulo app
├── build.gradle.kts                         # Configuração do projeto
└── README.md                                 # Este arquivo
```

### Arquitetura

O projeto segue o padrão **MVVM (Model-View-ViewModel)** com as seguintes camadas:

- **Model**: Entidades de dados (`Agua`, `Meta`)
- **View**: Telas Compose (`HomeScreen`, `AddWaterScreen`, etc.)
- **ViewModel**: Lógica de negócio e gerenciamento de estado (`HidroTimerViewModel`)
- **Repository**: Abstração da camada de dados (`HidroTimerRepository`)
- **DAO**: Acesso direto ao banco de dados (`AguaDao`, `MetaDao`)

---

## 📋 Regras de Negócio

### 1. Registro de Água

- O usuário pode adicionar água ingerida em **mililitros (ml)**
- Cada registro é armazenado com:
  - Quantidade de água (em ml)
  - Timestamp da ingestão (data e hora)
- O total ingerido é calculado somando todas as entradas do dia atual

### 2. Meta Diária

- A meta diária padrão é de **3000 ml (3 litros)**
- O usuário pode alterar a meta a qualquer momento
- Apenas uma meta pode existir no sistema (sempre com `id = 1`)
- Se não houver meta cadastrada, o sistema cria automaticamente uma meta padrão de 3000 ml

### 3. Reset Diário

- O contador de água ingerida é **resetado automaticamente** a cada novo dia
- O cálculo considera o dia atual (00:00:00 até 23:59:59)
- O sistema verifica a mudança de dia a cada minuto quando o app está em execução
- Apenas os registros do dia atual são considerados no total ingerido

### 4. Meta Atingida

- Quando o total ingerido atinge ou ultrapassa a meta diária:
  - O usuário é direcionado para a tela de "Meta Atingida"
  - O sistema emite uma vibração como feedback tátil
  - O usuário pode continuar adicionando água mesmo após atingir a meta

### 5. Persistência de Dados

- Todos os dados são armazenados localmente usando **Room Database**
- Os dados persistem entre sessões do aplicativo
- O banco de dados contém duas tabelas:
  - `agua`: Armazena todos os registros de água ingerida
  - `meta`: Armazena a meta diária do usuário

### 6. Operações CRUD

O aplicativo implementa todas as operações CRUD:

- **CREATE**: 
  - Inserir registro de água (`adicionarAgua()`)
  - Criar/atualizar meta (`atualizarMeta()`)
  
- **READ**: 
  - Ler total ingerido do dia (`getTotalIngerido()`)
  - Ler meta diária (`getMeta()`)
  - Listar todos os registros (`getAll()`)
  
- **UPDATE**: 
  - Atualizar meta diária (`atualizarMeta()`)
  
- **DELETE**: 
  - Deletar registro específico (`delete()`)
  - Deletar todos os registros (`deleteAll()`)

### 7. Easter Egg

- Funcionalidade secreta ativada ao clicar **10 vezes** no copinho na tela principal
- Cada clique emite uma vibração curta (100ms)
- Após 10 cliques, exibe uma imagem especial
- O contador é resetado quando a imagem é exibida

---

## 🛠️ Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação
- **Jetpack Compose**: Framework de UI declarativa
- **Wear Compose**: Componentes específicos para Wear OS
- **Room Database**: Persistência de dados local
- **ViewModel**: Gerenciamento de estado e lógica de negócio
- **Navigation Compose**: Navegação entre telas
- **Kotlin Coroutines & Flow**: Programação assíncrona e reativa
- **Material Design**: Design system do Android

---

## 📦 Dependências Principais

- `androidx.room:room-runtime` - Room Database
- `androidx.room:room-ktx` - Extensões Kotlin para Room
- `androidx.lifecycle:lifecycle-viewmodel-compose` - ViewModel para Compose
- `androidx.navigation:navigation-compose` - Navegação
- `androidx.wear.compose:compose-material` - Componentes Wear OS
- `androidx.compose.material3:material3` - Material Design 3

---

## 🎯 Funcionalidades de Criatividade e Inovação

- ✅ **App para Wear OS**: Desenvolvido especificamente para relógios inteligentes
- ✅ **Easter Egg**: Funcionalidade secreta interativa
- ✅ **Feedback Tátil**: Vibração para melhor experiência do usuário
- ✅ **Reset Automático Inteligente**: Detecção automática de mudança de dia
- ✅ **Design Pixel Art**: Interface com estilo retrô e visual único
- ✅ **Splash Screen**: Tela de apresentação com logo animada

---

## 👥 Desenvolvimento

Projeto desenvolvido como trabalho final da disciplina de **Linguagem de Programação Mobile**.

---

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais.

