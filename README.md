# HidroTimer

Aplicativo simples para Wear OS desenvolvido em Java. O objetivo é
fornecer um timer/hidrômetro para lembrar o usuário de beber água
durante o dia.

## Requisitos

Antes de iniciar, certifique-se de que possui:

-   Android Studio Iguana ou versão mais recente
-   Android SDK Platform 36
-   Wear OS 6.0 ARM 64 v8a System Image
-   Java 11 (já incluído no Android Studio)
-   Emulador Wear OS configurado

## Como rodar o projeto

### 1. Clone o repositório

    git clone https://github.com/OtavioMendesSantos/HidroTimer.git
    cd HidroTimer

### 2. Abra o projeto no Android Studio

-   Abra o Android Studio
-   Clique em "Open"
-   Selecione a pasta `HidroTimer`
-   Aguarde o Gradle sincronizar

### 3. Verifique as configurações de SDK

No Android Studio:

-   Vá em: File > Settings > Languages & Frameworks > Android SDK
-   Certifique-se de que os seguintes itens estão instalados:

1.  Android API Level 36\
2.  Wear OS 6.0 ARM 64 v8a System Image\
3.  Android SDK Build-Tools 36.x

### 4. Criar o emulador Wear OS

No Android Studio:

-   Acesse Device Manager
-   Clique em "Create Device"
-   Escolha Wear OS > Round (ou Pixel Watch)\
-   Selecione a imagem: Wear OS 6.0 ARM 64 v8a
-   Finalize a criação

### 5. Executar o aplicativo

-   No menu superior, selecione o emulador Wear OS criado
-   Clique no botão "Run" (ícone ▶)
-   O Android Studio instalará o app automaticamente no relógio virtual

## Estrutura do projeto

-   `app/src/main/java`
    Código-fonte Java da aplicação
-   `app/src/main/res`
    Arquivos de layout, temas e recursos
-   `build.gradle`
    Configuração do Android e dependências

## Solução de problemas

### Erro: "requires compileSdk 36 or later"

Abra `app/build.gradle` e defina:
    compileSdk 36
    targetSdk 36

Sincronize o Gradle.

### O emulador não inicia

Verifique se a CPU está configurada como ARM 64 v8a e se a aceleração
por hardware está habilitada na BIOS.

### O app não aparece no emulador

Abra a lista de aplicativos do Wear OS via:

    adb shell am start -a android.intent.action.MAIN -c android.intent.category.APP_LIST
