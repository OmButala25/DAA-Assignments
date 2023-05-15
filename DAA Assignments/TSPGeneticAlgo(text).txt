import random


class TSPGeneticAlgorithm:
    def __init__(self, distance_matrix, population_size, num_generations, mutation_rate):
        self.distance_matrix = distance_matrix
        self.population_size = population_size
        self.num_generations = num_generations
        self.mutation_rate = mutation_rate
        self.num_cities = len(distance_matrix)
        self.population = []

    def evolve(self):
        # Create the initial population
        for _ in range(self.population_size):
            chromosome = list(range(self.num_cities))
            random.shuffle(chromosome)
            self.population.append(chromosome)

        for _ in range(self.num_generations):
            self.population.sort(key=self.calculate_fitness)

            # Elitism: Preserve the best chromosome from the previous generation
            new_population = [self.population[0]]

            # Create the new population through selection and crossover
            while len(new_population) < self.population_size:
                # Selection: Tournament selection
                parent1 = self.tournament_selection()
                parent2 = self.tournament_selection()

                # Crossover: Order 1 crossover
                child = self.order1_crossover(parent1, parent2)

                # Mutation
                self.mutate(child)

                new_population.append(child)

            self.population = new_population

    def tournament_selection(self):
        tournament_size = self.population_size // 10  # Tournament size is 10% of the population size
        tournament_chromosomes = random.sample(self.population, tournament_size)
        return min(tournament_chromosomes, key=self.calculate_fitness)

    def order1_crossover(self, parent1, parent2):
        start_pos = random.randint(0, self.num_cities - 1)
        end_pos = random.randint(start_pos + 1, self.num_cities)

        child = [-1] * self.num_cities

        # Copy the subsequence from parent1 to the child
        child[start_pos:end_pos] = parent1[start_pos:end_pos]

        # Fill the remaining genes from parent2
        parent2_index = 0
        for i in range(self.num_cities):
            if parent2[parent2_index] not in child:
                # Find an empty position in the child and insert the gene from parent2
                empty_index = child.index(-1)
                child[empty_index] = parent2[parent2_index]
            parent2_index = (parent2_index + 1) % self.num_cities

        return child

    def mutate(self, chromosome):
        for i in range(self.num_cities):
            if random.random() < self.mutation_rate:
                swap_index = random.randint(0, self.num_cities - 1)
                chromosome[i], chromosome[swap_index] = chromosome[swap_index], chromosome[i]

    def calculate_fitness(self, chromosome):
        distance = 0
        for i in range(self.num_cities):
            current_city = chromosome[i]
            next_city = chromosome[(i + 1) % self.num_cities]  # Wrap around to the first city
            distance += self.distance_matrix[current_city][next_city]
        return distance

    def get_best_tour(self):
        best_chromosome = min(self.population, key=self.calculate_fitness)
        return best_chromosome

    def get_best_distance(self):
        best_chromosome = min(self.population, key=self.calculate_fitness)
        return self.calculate_fitness(best_chromosome)

distance_matrix = [
    [0, 10, 15, 20],
    [10, 0, 35, 25],
    [15, 35, 0, 30],
    [20, 25, 30, 0]
]

population_size = 50
num_generations = 100
mutation_rate = 0.02

tsp_ga = TSPGeneticAlgorithm(distance_matrix, population_size, num_generations, mutation_rate)
tsp_ga.evolve()

best_tour = tsp_ga.get_best_tour()
best_distance = tsp_ga.get_best_distance()

print("Best Tour:", best_tour)
print("Best Distance:", best_distance)

# The code implements a genetic algorithm to solve the Traveling Salesman Problem (TSP). The TSP involves finding the shortest possible route that visits a set of cities and returns to the starting city.

# Here's a step-by-step explanation of the code:

# The TSPGeneticAlgorithm class is initialized with the distance matrix, population size, number of generations, and mutation rate.

# The evolve method is called to start the evolution process. It creates an initial population of chromosomes (representing tours) randomly shuffled.

# The evolution process consists of several generations. In each generation, the population is sorted based on the fitness (total distance of the tour) of each chromosome.

# Elitism is applied by preserving the best chromosome from the previous generation in the new population.

# The new population is created through selection, crossover, and mutation. Tournament selection is used, where a subset of chromosomes competes, and the fittest one is selected as a parent.

# Order 1 crossover is applied to the selected parents, creating a child chromosome. A random subsequence from one parent is copied to the child, and the remaining genes are filled from the other parent.

# Mutation occurs with a certain probability for each gene in the child chromosome. If the random probability is below the mutation rate, two genes are swapped.

# After all new chromosomes are created, the new population replaces the previous population, and the process repeats for the specified number of generations.

# The calculate_fitness function calculates the fitness (total distance) of a given chromosome based on the distance matrix.

# The get_best_tour and get_best_distance functions return the best tour (chromosome) and its corresponding distance from the final population.

# Finally, the best tour and its distance are printed.

# The time complexity of the genetic algorithm depends on the population size, the number of generations, and the fitness evaluation (calculating the total distance). The overall complexity is generally considered to be O(P * G * N^2), where P is the population size, G is the number of generations, and N is the number of cities.