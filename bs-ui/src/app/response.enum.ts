export enum ResponseEnum {

  /**
   * SUCCESS code
   */
  SUCCESS = 200,
  /**
   * FAILED code
   */
  FAILED = 500,
  /**
   * TOKEN_EXPIRE code
   */
  TOKEN_EXPIRE = -9999,
  /**
   * RESP_UNAUTHORIZED
   */
  RESP_UNAUTHORIZED = 401,
  /**
   * RESP_UNRESOLVED FORM VALIDATE ERROR
   */
  RESP_UNRESOLVED = 406,
  /**
   * RESP_NO_RESPONSE
   */
  RESP_NO_RESPONSE = 0,
  /**
   * TOKEN_REFRESH code
   */
  TOKEN_REFRESH = -3,
  /**
   * FORM_DATA_INVALID code
   */
  FORM_DATA_INVALID = 417,
}
