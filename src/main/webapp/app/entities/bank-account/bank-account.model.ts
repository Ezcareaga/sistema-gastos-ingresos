export interface IBankAccount {
  id: number;
  name?: string | null;
  accountNumber?: string | null;
  balance?: number | null;
}

export type NewBankAccount = Omit<IBankAccount, 'id'> & { id: null };
