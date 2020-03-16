import { CustomDateFormatterPipe } from './custom-date-formatter.pipe';

describe('CustomDateFormatterPipe', () => {
  it('create an instance', () => {
    const pipe = new CustomDateFormatterPipe();
    expect(pipe).toBeTruthy();
  });
});
