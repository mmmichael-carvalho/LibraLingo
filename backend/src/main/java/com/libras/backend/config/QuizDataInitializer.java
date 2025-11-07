package com.libras.backend.config;

import com.libras.backend.model.quiz.Opcao;
import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.model.quiz.TipoPergunta;
import com.libras.backend.service.PerguntaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizDataInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(QuizDataInitializer.class);
    private final PerguntaService perguntaService;

    public QuizDataInitializer(PerguntaService perguntaService) {
        this.perguntaService = perguntaService;
    }

    @Override
    public void run(ApplicationArguments args) {


        if (!perguntaService.listarTodas().isEmpty()) {

            return;
        }

        criarNivel1_CumprimentosBasicos();
        criarNivel2_ConversasCotidianas();
        criarNivel3_Familia();
        criarNivel4_Alimentos();
        criarNivel5_Lugares();

    }

    private void criarNivel1_CumprimentosBasicos() {

        // 1. OI
        Pergunta pergunta1 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel1/oi1.png,/images/nivel1/oi12.png",
                List.of("Bom dia", "Boa tarde", "Oi", "Tudo bem?"),
                2
        );
        pergunta1.setLevel(1);
        perguntaService.salvar(pergunta1);


        // 2. DESCULPA
        Pergunta pergunta2 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel1/desculpa1.png",
                List.of("Tchau", "Por Favor", "Obrigado", "Desculpa"),
                3
        );

        pergunta2.setLevel(1);
        perguntaService.salvar(pergunta2);

        // 3. TCHAU
        Pergunta pergunta3 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel1/tchau1.png",
                List.of("Com licença", "Boa noite", "Tchau", "Obrigado"),
                2
        );
        pergunta3.setLevel(1);
        perguntaService.salvar(pergunta3);

        // 4. OBRIGADO
        Pergunta pergunta4 = criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Obrigado",
                List.of("/images/nivel1/obrigado1.png", "/images/null/idade.png", "/images/nivel1/tchau1.png", "/images/nivel2/bomdia24.png"),
                0
        );
        pergunta4.setLevel(1);
        perguntaService.salvar(pergunta4);

        // 5. EU
        Pergunta pergunta5 = criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Eu",
                List.of("/images/null/mae.png", "/images/nivel1/eu1.png", "/images/null/trem.png", "/images/null/quasepao1.png"),
                1
        );
        pergunta5.setLevel(1);
        perguntaService.salvar(pergunta5);
    }

    private void criarNivel2_ConversasCotidianas() {

        // 1. BOM DIA
        Pergunta pergunta1 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel2/bomdia2.png,/images/nivel2/bomdia22.png,/images/nivel2/bomdia23.png,/images/nivel2/bomdia24.png",
                List.of("Bom dia", "Tudo bem?", "Prazer em conhecer", "Boa tarde"),
                0
        );
        pergunta1.setLevel(2);
        perguntaService.salvar(pergunta1);


        // 2. CARRO
        Pergunta pergunta2 = criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Carro",
                List.of("/images/nivel2/moto2.png", "/images/nivel2/onibus2.png", "/images/nivel2/trem2.png", "/images/nivel2/carro2.png"),
                3
        );
        pergunta2.setLevel(2);
        perguntaService.salvar(pergunta2);

        // 3. EU VOU
        Pergunta pergunta3 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel2/eu2.png,/images/nivel2/ir2.png",
                List.of("Eu sou", "Estou com fome", "Eu vou", "Eu e você"),
                2
        );
        pergunta3.setLevel(2);
        perguntaService.salvar(pergunta3);

        // 4. ÔNIBUS
        Pergunta pergunta4 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel2/onibus2.png,/images/nivel2/onibus22.png",
                List.of("Carro", "Moto", "Ônibus", "Trem"),
                2
        );
        pergunta4.setLevel(2);
        perguntaService.salvar(pergunta4);


        // 5. Idade
        Pergunta pergunta5 = criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Idade",
                List.of("/images/nivel2/gemini2.png", "/images/nivel2/idade2.png", "/images/nivel2/cinema2.png", "/images/nivel2/cafe2.png"),
                1
        );
        pergunta5.setLevel(2);
        perguntaService.salvar(pergunta5);
    }

    private void criarNivel3_Familia() {

        // 1. MÃE
        Pergunta pergunta1 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel3/mae3.png,/images/nivel3/mae32.png",
                List.of("Pai", "Mãe", "Irmão", "Avó"),
                1
        );
        pergunta1.setLevel(3);
        perguntaService.salvar(pergunta1);

        // 2. Tio
        Pergunta pergunta2 = criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Tio",
                List.of("/images/null/obrigado.png", "/images/null/joia.png", "/images/nivel3/tio3.png", "/images/null/quaseirmao.png"),
                2
        );
        pergunta2.setLevel(3);
        perguntaService.salvar(pergunta2);

        // 3. PAI
        Pergunta pergunta3 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel3/pai3.png,/images/nivel3/mae32.png",
                List.of("Pai", "Tio", "Avô", "Primo"),
                0
        );
        pergunta3.setLevel(3);
        perguntaService.salvar(pergunta3);

        // 4. Amigo
        Pergunta pergunta4 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel3/amigo3.png",
                List.of("Primo", "Amigo", "Irmão", "Conhecido"),
                1
        );
        pergunta4.setLevel(3);
        perguntaService.salvar(pergunta4);

        // 5. Familia
        Pergunta pergunta5 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel3/familia31.png,/images/nivel3/familia32.png",
                List.of("Irmãos", "Time", "Grupo", "Familia"),
                3
        );
        pergunta5.setLevel(3);
        perguntaService.salvar(pergunta5);
    }

    private void criarNivel4_Alimentos() {

        // 1. ÁGUA
        Pergunta pergunta1 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel4/agua41.png,/images/nivel4/agua42.png",
                List.of("Leite", "Café", "Água", "Suco"),
                2
        );
        pergunta1.setLevel(4);
        perguntaService.salvar(pergunta1);

        // 2. Restaurante
        Pergunta pergunta2 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel4/restaurante41.png,/images/nivel4/restaurante42.png",
                List.of("Bebida", "Restaurante", "Cozinha", "Jantar"),
                1
        );
        pergunta2.setLevel(4);
        perguntaService.salvar(pergunta2);

        // 3. CAFÉ
        Pergunta pergunta3 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel4/cafe41.png,/images/nivel4/cafe42.png",
                List.of("Chá", "Bebida", "Leite", "Café"),
                3
        );
        pergunta3.setLevel(4);
        perguntaService.salvar(pergunta3);

        // 4. Comer
        Pergunta pergunta4 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel4/comer41.png,/images/nivel4/comer42.png",
                List.of("Comer", "Fome", "Vem", "Jantar"),
                0
        );
        pergunta4.setLevel(4);
        perguntaService.salvar(pergunta4);

        // 5. Arroz
        Pergunta pergunta5 = criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Arroz",
                List.of("/images/null/quasepao1.png", "/images/null/shoping.png", "/images/nivel4/arroz4.png", "/images/null/bomdia.png"),
                2
        );
        pergunta5.setLevel(4);
        perguntaService.salvar(pergunta5);
    }

    private void criarNivel5_Lugares() {

        // 1. Casa
        Pergunta pergunta1 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel5/casa5.png",
                List.of("Casa", "Escola", "Hospital", "Mercado"),
                0
        );
        pergunta1.setLevel(5);
        perguntaService.salvar(pergunta1);

        // 2. HOSPITAL
        Pergunta pergunta2 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel5/hospital51.png,/images/nivel5/hospital52.png",
                List.of("Hospital", "Hotel", "Escola", "Banco"),
                0
        );
        pergunta2.setLevel(5);
        perguntaService.salvar(pergunta2);

        // 3. Shopping
        Pergunta pergunta3 = criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Shopping",
                List.of("/images/null/idade.png", "/images/null/trem.png", "/images/null/moto.png", "/images/nivel5/shoping5.png"),
                3
        );
        pergunta3.setLevel(5);
        perguntaService.salvar(pergunta3);

        // 2. Banheiro
        Pergunta pergunta4 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel5/banheiro5.png",
                List.of("Hospital", "Hotel", "Banheiro", "Banco"),
                2
        );
        pergunta4.setLevel(5);
        perguntaService.salvar(pergunta4);

        // 5. Cinema
        Pergunta pergunta5 = criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Cinema",
                List.of("/images/nivel5/cinema5.png", "/images/null/onibus.png", "/images/null/pai.png", "/images/null/quasepai2.png"),
                0
        );
        pergunta5.setLevel(5);
        perguntaService.salvar(pergunta5);
    }

    private Pergunta criarPergunta(
            TipoPergunta tipo,
            String prompt,
            List<String> opcoes,
            int indiceCorreto
    ) {
        Pergunta pergunta = new Pergunta();

        pergunta.setTipo(tipo);
        pergunta.setPrompt(prompt);
        pergunta.setIndiceCorreto(indiceCorreto);

        List<Opcao> opcoesMapeadas = opcoes.stream().map(valor -> {
            Opcao opcao = new Opcao();
            if (tipo == TipoPergunta.TEXTO_PARA_IMAGEM) {
                opcao.setTexto(null);
                opcao.setImagemUrl(valor);
            } else {
                opcao.setTexto(valor);
                opcao.setImagemUrl(null);
            }
            return opcao;
        }).toList();

        pergunta.setOpcoes(opcoesMapeadas);
        return pergunta;
    }
}