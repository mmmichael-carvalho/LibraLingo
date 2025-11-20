import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { SessionService } from '../../services/session.service';

interface NivelInfo {
  numero: number;
  nome: string;
  emoji: string;
  descricao: string;
  totalPerguntas: number;
  disponivel: boolean;
  completado: boolean;
  pontuacao?: number;
  emBreve?: boolean;
}

@Component({
  selector: 'level-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './level-list.component.html',
  styleUrls: ['./level-list.component.scss']
})
export class LevelListComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>();

  niveis: NivelInfo[] = [];
  expandedCard: number | null = null;

  constructor(private sessionService: SessionService) {}

  ngOnInit() {
    this.sessionService.progresso$
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => {
        this.atualizarDados();
      });

    this.atualizarDados();
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }

  public toggleCard(index: number): void {
    if (this.expandedCard === index) {
      this.expandedCard = null;
    } else {
      this.expandedCard = index;
    }
  }

  private atualizarDados(): void {
    this.niveis = this.sessionService.getNiveisComStatus();
  }
}
