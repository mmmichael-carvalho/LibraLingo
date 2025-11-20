import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface NivelProgresso {
  nivel: number;
  pontuacao?: number;
  totalPerguntas: number;
  completado: boolean;
  desbloqueado: boolean;

}

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private progressoSubject = new BehaviorSubject<Map<number, NivelProgresso>>(new Map());
  public progresso$ = this.progressoSubject.asObservable();

  private readonly NIVEIS_CONFIG = [
    { numero: 1, nome: 'Cumprimentos Básicos', emoji: '👋', descricao: 'Cumprimentos básicos em LIBRAS', totalPerguntas: 5 },
    { numero: 2, nome: 'Conversas Cotidianas', emoji: '🗣️', descricao: 'Palavras mais usadas em libras', totalPerguntas: 5 },
    { numero: 3, nome: 'Família', emoji: '👨‍👩‍👧‍👦', descricao: 'Sinais da família', totalPerguntas: 5 },
    { numero: 4, nome: 'Alimentos', emoji: '🍎', descricao: 'Comidas e bebidas', totalPerguntas: 5 },
    { numero: 5, nome: 'Lugares', emoji: '🏠', descricao: 'Lugares importantes', totalPerguntas: 5 }
  ];

  constructor() {
    this.inicializarProgresso();
  }

  private inicializarProgresso(): void {
    const progressoInicial = new Map<number, NivelProgresso>();

    this.NIVEIS_CONFIG.forEach(config => {
      progressoInicial.set(config.numero, {
        nivel: config.numero,
        totalPerguntas: config.totalPerguntas,
        completado: false,
        desbloqueado: config.numero === 1,

      });
    });

    this.progressoSubject.next(progressoInicial);
  }

  public registrarResultado(nivel: number, pontuacao: number, totalPerguntas: number): void {
    const progressoAtual = this.progressoSubject.getValue();
    const nivelAtual = progressoAtual.get(nivel);

    if (nivelAtual) {


nivelAtual.pontuacao = pontuacao;

      if (!nivelAtual.completado) {
        nivelAtual.completado = true;
      }

      const passou = pontuacao >= 4;

      if (passou && nivel < 5) {
        const proximoNivel = progressoAtual.get(nivel + 1);
        if (proximoNivel && !proximoNivel.desbloqueado) {
          proximoNivel.desbloqueado = true;
        }
      }

      progressoAtual.set(nivel, nivelAtual);
      this.progressoSubject.next(new Map(progressoAtual));
    }
  }

  public isNivelDesbloqueado(nivel: number): boolean {
    const progresso = this.progressoSubject.getValue();
    const nivelData = progresso.get(nivel);
    return nivelData?.desbloqueado || false;
  }

//   public isNivelCompletado(nivel: number): boolean {
//     const progresso = this.progressoSubject.getValue();
//     const nivelData = progresso.get(nivel);
//     return nivelData?.completado || false;
//   }

//   public getPontuacaoNivel(nivel: number): number | undefined {
//     const progresso = this.progressoSubject.getValue();
//     return progresso.get(nivel)?.pontuacao;
//   }

  public getNiveisComStatus() {
    const progresso = this.progressoSubject.getValue();

    return this.NIVEIS_CONFIG.map(config => {
      const progressoNivel = progresso.get(config.numero);
      return {
        ...config,
        disponivel: progressoNivel?.desbloqueado || false,
        completado: progressoNivel?.completado || false,
        pontuacao: progressoNivel?.pontuacao,
      };
    });
  }


}
