export class ExceptionHandler {
  public static handler417UnCaughtException(error: Error) {
    if (error.message.indexOf('{"code":417}')) {
      return;
    }
    throw error;
  }
}
