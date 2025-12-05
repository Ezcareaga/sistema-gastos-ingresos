import { CategoryType } from 'app/entities/enumerations/category-type.model';

export interface ICategory {
  id: number;
  name?: string | null;
  description?: string | null;
  type?: keyof typeof CategoryType | null;
}

export type NewCategory = Omit<ICategory, 'id'> & { id: null };
