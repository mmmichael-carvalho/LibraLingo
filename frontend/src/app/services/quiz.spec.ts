import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { QuizService } from './quiz.service';
import { environment } from '../environments/environment';



@Injectable({ providedIn: 'root' })
export class ApiService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getWords() {
    return this.http.get(`${this.apiUrl}/words`);
  }
}
  describe('QuizService', () => {
    let service: QuizService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(QuizService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
