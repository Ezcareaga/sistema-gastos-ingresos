import dayjs from 'dayjs/esm';
import { ICategory } from 'app/entities/category/category.model';
import { IBankAccount } from 'app/entities/bank-account/bank-account.model';
import { IContact } from 'app/entities/contact/contact.model';
import { TransactionType } from 'app/entities/enumerations/transaction-type.model';

export interface ITransaction {
  id: number;
  date?: dayjs.Dayjs | null;
  amount?: number | null;
  type?: keyof typeof TransactionType | null;
  description?: string | null;
  reference?: string | null;
  category?: ICategory | null;
  bankAccount?: IBankAccount | null;
  contact?: IContact | null;
}

export type NewTransaction = Omit<ITransaction, 'id'> & { id: null };
