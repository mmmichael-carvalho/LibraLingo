import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { ResultadoQuizDTO, RespostaDetalhada } from '../../models';

@Component({
  selector: 'quiz-result',
  standalone: true,
  imports: [CommonModule, RouterModule],
 template: `
   <div class="result-container">

     <header class="result-header">
       <div class="header-content">
         <button class="back-btn" (click)="voltarNiveis()">
           <span>←</span> Voltar aos Níveis
         </button>
         <div class="header-info">
           <h1>Resultado do Quiz - Nível {{ level }}</h1>
         </div>
       </div>
     </header>

     <div class="result-content">
       <!-- Card de Resumo -->
       <div class="result-card">
         <h2>{{ tituloResultado }}</h2>

         <div class="score-display">
           <div class="score-circle">
             <span class="score-number">{{ resultado?.pontuacao || 0 }}</span>
             <span class="score-total">/ {{ totalPerguntas }}</span>
           </div>
         </div>

         <p class="result-message">{{ resultado?.mensagem || 'Resultado não disponível' }}</p>

         <div *ngIf="respostasDetalhadas && respostasDetalhadas.length > 0" class="questions-summary-simple">
           <p class="acertos-text">
             <strong>Acertos:</strong> {{ getQuestoesAcertadas() }}
           </p>
         </div>

         <div class="action-buttons">
           <button (click)="voltarNiveis()" class="btn btn-primary">
             Escolher Outro Nível
           </button>
           <button (click)="refazerQuiz()" class="btn btn-secondary">
             Refazer Este Nível
           </button>
         </div>
       </div>
     </div>
   </div>
 `,
  styles: [`

    .questions-summary-simple {
      margin: 25px 0;
      padding: 20px;
      background: #1a202c;
      border-radius: 8px;
      border: 1px solid #4a5568;
    }

    .acertos-text {
      color: #a0aec0;
      font-size: 1rem;
      margin: 0;
      text-align: center;
    }

    .acertos-text strong {
      color: white;
    }

    .result-container {
      min-height: 100vh;
      background: #1a202c;
      display: flex;
      flex-direction: column;
    }

    .result-header {
      padding: 25px;
      background: #2d3748;
      border-bottom: 1px solid #4a5568;
    }

    .header-content {
      max-width: 800px;
      margin: 0 auto;
      display: flex;
      align-items: center;
      gap: 20px;
    }

    .back-btn {
      background: #3182ce;
      color: white;
      border: none;
      padding: 10px 15px;
      border-radius: 8px;
      font-weight: 500;
      cursor: pointer;
      display: flex;
      align-items: center;
      gap: 8px;
      transition: all 0.2s ease;
    }

    .back-btn:hover {
      background: #2c5282;
    }

    .header-info {
      flex: 1;
      text-align: center;
      color: white;
    }

    .header-info h1 {
      font-size: 1.5rem;
      margin: 0;
      font-weight: 500;
    }

    .result-content {
      flex: 1;
      padding: 40px 20px;
      max-width: 800px;
      margin: 0 auto;
      width: 100%;
    }

    .result-card {
      background: #2d3748;
      border: 1px solid #4a5568;
      border-radius: 16px;
      padding: 40px;
      text-align: center;
      margin-bottom: 30px;
    }

    h2 {
      color: white;
      font-size: 2rem;
      font-weight: 500;
      margin-bottom: 30px;
    }

    .score-circle {
      width: 120px;
      height: 120px;
      border-radius: 50%;
      background: linear-gradient(45deg, #3182ce, #667eea);
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      margin: 0 auto 20px;
      box-shadow: 0 10px 30px rgba(49, 130, 206, 0.3);
    }

    .score-number {
      font-size: 2.5rem;
      font-weight: bold;
      color: white;
    }

    .score-total {
      font-size: 1rem;
      color: rgba(255, 255, 255, 0.8);
    }

    .result-message {
      color: #a0aec0;
      font-size: 1.1rem;
      margin: 20px 0;
    }

    .questions-summary {
      background: #1a202c;
      border-radius: 12px;
      padding: 25px;
      margin: 30px 0;
      border: 1px solid #4a5568;
    }

    .summary-header h3 {
      color: white;
      font-size: 1.2rem;
      margin: 0 0 20px 0;
      font-weight: 500;
    }

    .questions-grid {
      display: flex;
      justify-content: center;
      gap: 12px;
      flex-wrap: wrap;
      margin-bottom: 20px;
    }

    .question-box {
      width: 60px;
      height: 60px;
      border-radius: 12px;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      cursor: pointer;
      transition: all 0.2s ease;
      border: 2px solid;
    }

    .question-box.correct {
      background: rgba(72, 187, 120, 0.1);
      border-color: #48bb78;
    }

    .question-box.incorrect {
      background: rgba(245, 101, 101, 0.1);
      border-color: #f56565;
    }

    .question-box:hover {
      transform: scale(1.1);
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
    }

    .question-number {
      font-size: 0.9rem;
      color: #a0aec0;
      font-weight: 600;
    }

    .question-status {
      font-size: 1.2rem;
      font-weight: bold;
    }

    .question-box.correct .question-status {
      color: #48bb78;
    }

    .question-box.incorrect .question-status {
      color: #f56565;
    }

    .legend {
      display: flex;
      justify-content: center;
      gap: 30px;
      margin-top: 15px;
      padding-top: 15px;
      border-top: 1px solid #4a5568;
    }

    .legend-item {
      display: flex;
      align-items: center;
      gap: 8px;
      color: #a0aec0;
      font-size: 0.9rem;
    }

    .legend-box {
      width: 20px;
      height: 20px;
      border-radius: 4px;
      border: 2px solid;
    }

    .legend-box.correct {
      background: rgba(72, 187, 120, 0.1);
      border-color: #48bb78;
    }

    .legend-box.incorrect {
      background: rgba(245, 101, 101, 0.1);
      border-color: #f56565;
    }

    .action-buttons {
      display: flex;
      gap: 15px;
      justify-content: center;
      flex-wrap: wrap;
    }

    .btn {
      padding: 12px 24px;
      border-radius: 8px;
      font-weight: 500;
      cursor: pointer;
      transition: all 0.2s ease;
      min-width: 160px;
    }

    .btn-primary {
      background: #3182ce;
      color: white;
      border: none;
    }

    .btn-primary:hover {
      background: #2c5282;
      transform: translateY(-1px);
    }

    .btn-secondary {
      background: transparent;
      color: #a0aec0;
      border: 1px solid #4a5568;
    }

    .btn-secondary:hover {
      border-color: #3182ce;
      color: white;
      background: rgba(49, 130, 206, 0.1);
    }

    @media (max-width: 768px) {
      .result-card {
        padding: 25px;
      }

      .questions-grid {
        gap: 8px;
      }

      .question-box {
        width: 50px;
        height: 50px;
      }

      .legend {
        flex-direction: column;
        gap: 10px;
        align-items: center;
      }
    }
  `]
})
export class QuizResultComponent {
  resultado: ResultadoQuizDTO | null = null;
  respostasDetalhadas: RespostaDetalhada[] | null = null;
  level: number = 1;
  totalPerguntas: number = 5;

  constructor(private router: Router) {
    const navigation = this.router.getCurrentNavigation();
    const state = navigation?.extras.state;

    if (state) {
      this.resultado = state['resultado'];
      this.level = state['level'] || 1;
      this.respostasDetalhadas = state['respostasDetalhadas'] || null;

      if (this.respostasDetalhadas) {
        this.totalPerguntas = this.respostasDetalhadas.length;
      }
    }
  }

getQuestoesAcertadas(): string {
  if (!this.respostasDetalhadas) return 'Nenhuma';

  const acertos = this.respostasDetalhadas
    .map((r, i) => r.acertou ? i + 1 : null)
    .filter(n => n !== null);

  if (acertos.length === 0) return 'Nenhuma';

  return acertos.join(', ');
}

  get totalAcertos(): number {
    if (!this.respostasDetalhadas) return 0;
    return this.respostasDetalhadas.filter(r => r.acertou).length;
  }

  get totalErros(): number {
    if (!this.respostasDetalhadas) return 0;
    return this.respostasDetalhadas.filter(r => !r.acertou).length;
  }

  get tituloResultado(): string {
    const acertos = this.resultado?.pontuacao || 0;
    if (acertos >= 4) {
      return 'Proximo Nível desbloqueado!';
    } else {
      return 'Tente novamente';
    }
  }

  getTooltip(resposta: RespostaDetalhada, index: number): string {
    const numero = index + 1;
    return resposta.acertou
      ? `Questão ${numero}: Correta ✓`
      : `Questão ${numero}: Incorreta ✗`;
  }

  voltarNiveis(): void {
    this.router.navigate(['']);
  }

  refazerQuiz(): void {
    this.router.navigate(['/quiz'], { queryParams: { level: this.level } });
  }
}
