export const environment = {
  production: false,
  apiUrl: 'https://libralingo.onrender.com/api'
};
```

E que **NÃO EXISTE** nenhum lugar no código com `.vercel.app` hardcoded.

---

### 2️⃣ **Backend** (Imagem 2 + logs):
O Spring Boot não consegue conectar no PostgreSQL porque a URL está errada.

**❌ Erro:**
```
postgresql://libralingo:senha@libralingo-db:5432/libralingo
```

**✅ Deve ser:**
```
jdbc:postgresql://libralingo-db:5432/libralingo
