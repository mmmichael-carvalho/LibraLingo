import { environment } from '../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {
  QuestaoDTO,
  RespostaQuizDTO,
  ResultadoQuizDTO
} from '../models';


import { environment } from '../environments/environment';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getWords() {
    return this.http.get(`${this.apiUrl}/words`);
  }
}

@Injectable({ providedIn: 'root' })
export class QuizService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getQuizzes() {
    return this.http.get(`${this.apiUrl}/quizzes`);
  }

  getQuestoesPorNivel(level: number): Observable<QuestaoDTO[]> {
    return this.http.get<QuestaoDTO[]>(`${this.apiUrl}/quiz/levels/${level}/questions`);
  }

  getTodasPerguntas(): Observable<QuestaoDTO[]> {
    return this.http.get<QuestaoDTO[]>(`${this.apiUrl}/quiz/perguntas`);
  }

  submitRespostas(respostas: RespostaQuizDTO[]): Observable<ResultadoQuizDTO> {
    return this.http.post<ResultadoQuizDTO>(
      `${this.apiUrl}/quiz/respostas`,
      respostas
    );
  }
}
