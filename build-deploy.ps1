

Write-Host ""
Write-Host "===============================================================" -ForegroundColor Cyan
Write-Host "              LibraLingo Build Script" -ForegroundColor Yellow
Write-Host "     Build unificado: Angular + Spring Boot" -ForegroundColor White
Write-Host "===============================================================" -ForegroundColor Cyan
Write-Host ""

if (-not (Test-Path "frontend") -or -not (Test-Path "backend")) {
    Write-Host "[ERRO] Execute este script da raiz do projeto!" -ForegroundColor Red
    Write-Host "   Estrutura esperada:" -ForegroundColor Yellow
    Write-Host "     novo-libralingo/" -ForegroundColor White
    Write-Host "     |- frontend/" -ForegroundColor White
    Write-Host "     |- backend/" -ForegroundColor White
    Write-Host "     |- build-deploy.ps1" -ForegroundColor White
    exit 1
}

$StartTime = Get-Date

Write-Host "===============================================================" -ForegroundColor Cyan
Write-Host "[1/4] Building Frontend (Angular)..." -ForegroundColor Yellow
Write-Host "===============================================================" -ForegroundColor Cyan
Write-Host ""

Set-Location frontend

if (-not (Test-Path "node_modules")) {
    Write-Host "[INFO] node_modules nao encontrado. Executando npm install..." -ForegroundColor Yellow
    npm install
    Write-Host ""
}

Write-Host "[BUILD] Executando build de producao..." -ForegroundColor Yellow
npm run build -- --configuration production

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "[ERRO] Frontend build falhou!" -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "[OK] Frontend build concluido com sucesso!" -ForegroundColor Green
Write-Host ""

Write-Host "===============================================================" -ForegroundColor Cyan
Write-Host "[2/4] Copiando build para backend..." -ForegroundColor Yellow
Write-Host "===============================================================" -ForegroundColor Cyan
Write-Host ""

Set-Location ..
$BackendStatic = "backend\src\main\resources\static"

if (-not (Test-Path $BackendStatic)) {
    New-Item -ItemType Directory -Path $BackendStatic | Out-Null
}

Write-Host "[CLEAN] Limpando build anterior..." -ForegroundColor Yellow
Get-ChildItem -Path $BackendStatic -Recurse | Remove-Item -Force -Recurse -ErrorAction SilentlyContinue

$DistPath = $null
$PossiblePaths = @(
    "frontend\dist\browser",
    "frontend\dist\libralingo\browser",
    "frontend\dist\libralingo",
    "frontend\dist"
)

foreach ($path in $PossiblePaths) {
    if (Test-Path $path) {
        $DistPath = $path
        break
    }
}

if (-not $DistPath) {
    Write-Host "[ERRO] Pasta dist nao encontrada!" -ForegroundColor Red
    exit 1
}

Write-Host "[INFO] Build encontrado em: $DistPath" -ForegroundColor Green

Write-Host "[COPY] Copiando arquivos..." -ForegroundColor Yellow
Copy-Item -Path "$DistPath\*" -Destination $BackendStatic -Recurse -Force

if ($LASTEXITCODE -eq 0 -or $?) {
    $FileCount = (Get-ChildItem -Path $BackendStatic -Recurse -File).Count
    Write-Host ""
    Write-Host "[OK] $FileCount arquivos copiados com sucesso!" -ForegroundColor Green

    Write-Host "[INFO] Arquivos principais:" -ForegroundColor Yellow
    Get-ChildItem -Path $BackendStatic -Filter "*.html" | ForEach-Object {
        $sizeKB = [math]::Round($_.Length / 1024, 2)
        Write-Host "  - $($_.Name) ($sizeKB KB)" -ForegroundColor White
    }
} else {
    Write-Host ""
    Write-Host "[ERRO] Erro ao copiar arquivos!" -ForegroundColor Red
    exit 1
}
Write-Host ""

Write-Host "===============================================================" -ForegroundColor Cyan
Write-Host "[3/4] Building Backend (Spring Boot)..." -ForegroundColor Yellow
Write-Host "===============================================================" -ForegroundColor Cyan
Write-Host ""

Set-Location backend

$MavenInstalled = Get-Command mvn -ErrorAction SilentlyContinue
if (-not $MavenInstalled) {
    Write-Host "[ERRO] Maven nao encontrado! Instale Maven primeiro." -ForegroundColor Red
    Write-Host "   Download: https://maven.apache.org/download.cgi" -ForegroundColor Yellow
    exit 1
}

Write-Host "[CLEAN] Limpando builds anteriores..." -ForegroundColor Yellow
mvn clean

Write-Host ""
Write-Host "[BUILD] Gerando JAR de producao..." -ForegroundColor Yellow
mvn package -DskipTests

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "[ERRO] Backend build falhou!" -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "[OK] Backend build concluido com sucesso!" -ForegroundColor Green
Write-Host ""

Write-Host "===============================================================" -ForegroundColor Cyan
Write-Host "[4/4] Verificando JAR final..." -ForegroundColor Yellow
Write-Host "===============================================================" -ForegroundColor Cyan
Write-Host ""

$JarFile = Get-ChildItem -Path "target" -Filter "*.jar" -File | Where-Object { $_.Name -notlike "*-original.jar" } | Select-Object -First 1

if ($JarFile) {
    $FileSizeMB = [math]::Round($JarFile.Length / 1MB, 2)
    
    Write-Host "[OK] JAR criado com sucesso!" -ForegroundColor Green
    Write-Host ""
    Write-Host "[INFO] Informacoes do JAR:" -ForegroundColor Yellow
    Write-Host "  - Nome: $($JarFile.Name)" -ForegroundColor Green
    Write-Host "  - Tamanho: $FileSizeMB MB" -ForegroundColor Green
    Write-Host "  - Caminho: $($JarFile.FullName)" -ForegroundColor Green

    Write-Host ""
    Write-Host "[CHECK] Verificando conteudo do JAR..." -ForegroundColor Yellow
    
    $JarContents = jar tf $JarFile.FullName
    
    if ($JarContents -like "*static/index.html*") {
        Write-Host "  [OK] Frontend incluido (index.html encontrado)" -ForegroundColor Green
    } else {
        Write-Host "  [WARN] Frontend pode nao estar incluido!" -ForegroundColor Yellow
    }
    
    if ($JarContents -like "*static/main*.js*") {
        Write-Host "  [OK] JavaScript encontrado" -ForegroundColor Green
    } else {
        Write-Host "  [WARN] Arquivos JavaScript nao encontrados!" -ForegroundColor Yellow
    }
} else {
    Write-Host "[ERRO] JAR nao encontrado!" -ForegroundColor Red
    exit 1
}

$EndTime = Get-Date
$Duration = ($EndTime - $StartTime).TotalSeconds

Write-Host ""
Write-Host "===============================================================" -ForegroundColor Cyan
Write-Host "[SUCCESS] Build completo! Pronto para deploy!" -ForegroundColor Green
Write-Host "===============================================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Tempo total: $([math]::Round($Duration, 0))s" -ForegroundColor Yellow
Write-Host ""
Write-Host "PROXIMOS PASSOS:" -ForegroundColor Yellow
Write-Host ""
Write-Host "  1. Testar localmente:" -ForegroundColor Green
Write-Host "     cd backend" -ForegroundColor Cyan
Write-Host "     java -jar $($JarFile.Name)" -ForegroundColor Cyan
Write-Host "     Acesse: http://localhost:8080" -ForegroundColor Cyan
Write-Host ""
Write-Host "  2. Verificar funcionalidades:" -ForegroundColor Green
Write-Host "     - Frontend carrega corretamente" -ForegroundColor White
Write-Host "     - API responde: http://localhost:8080/api/quiz" -ForegroundColor White
Write-Host "     - Quizzes funcionam" -ForegroundColor White
Write-Host ""
Write-Host "  3. Fazer deploy:" -ForegroundColor Green
Write-Host "     - Railway: Conectar GitHub e fazer push" -ForegroundColor White
Write-Host "     - Render: Upload do JAR ou conectar GitHub" -ForegroundColor White
Write-Host ""
Write-Host "Boa sorte com o deploy!" -ForegroundColor Yellow
Write-Host ""

Set-Location ..